import csv
from pathlib import Path

import matplotlib.pyplot as plt


CSV_PATH = Path("results/results.csv")
PLOT_DIR = Path("results/plots")


def series_label(row):
    if row["variant"] == "-":
        return row["structure"]
    return f'{row["structure"]} ({row["variant"]})'


def main():
    with CSV_PATH.open(newline="", encoding="utf-8") as file:
        rows = list(csv.DictReader(file))

    PLOT_DIR.mkdir(parents=True, exist_ok=True)

    for workload in ("W1", "W2", "W3", "W4"):
        workload_rows = [
            row for row in rows if row["workload"] == workload
        ]
        labels = sorted({series_label(row) for row in workload_rows})

        # Time vs n
        fig, ax = plt.subplots(figsize=(8, 5))

        for label in labels:
            series = sorted(
                (row for row in workload_rows if series_label(row) == label),
                key=lambda row: int(row["n"]),
            )
            sizes = [int(row["n"]) for row in series]
            times = [float(row["time_ms"]) for row in series]
            ax.plot(sizes, times, marker="o", label=label)

        ax.set_xscale("log")
        ax.set_yscale("log")
        ax.set_xlabel("Input size (n)")
        ax.set_ylabel("Median time (ms)")
        ax.set_title(f"{workload}: Time vs n")
        ax.grid(True, which="both", alpha=0.3)
        ax.legend()
        fig.tight_layout()
        fig.savefig(PLOT_DIR / f"{workload}_time.png", dpi=180)
        plt.close(fig)

        # Steps, moves, and comparisons vs n
        fig, axes = plt.subplots(3, 1, figsize=(8, 10), sharex=True)

        for ax, metric in zip(
                axes,
                ("steps", "moves", "comparisons"),
        ):
            all_counts = [int(row[metric]) for row in workload_rows]

            ax.set_xscale("log")
            ax.set_ylabel(metric.capitalize())
            ax.grid(True, which="both", alpha=0.3)

            if all(count == 0 for count in all_counts):
                ax.set_ylim(-1, 1)
                ax.set_yticks([0])
                ax.text(
                    0.5,
                    0.5,
                    "All counts are zero",
                    transform=ax.transAxes,
                    ha="center",
                    va="center",
                )
                continue

            # Symmetric log scale keeps small values and zero-valued series visible.
            ax.set_yscale("symlog", linthresh=1)

            for label in labels:
                series = sorted(
                    (row for row in workload_rows if series_label(row) == label),
                    key=lambda row: int(row["n"]),
                )
                sizes = [int(row["n"]) for row in series]
                counts = [int(row[metric]) for row in series]
                ax.plot(sizes, counts, marker="o", label=label)

            ax.legend()

        axes[-1].set_xlabel("Input size (n)")
        fig.suptitle(f"{workload}: Operation counts vs n")
        fig.tight_layout()
        fig.savefig(PLOT_DIR / f"{workload}_operations.png", dpi=180)
        plt.close(fig)

    print(f"Plots saved to {PLOT_DIR}")


if __name__ == "__main__":
    main()