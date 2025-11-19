const grid = document.getElementById("grid");

// Create 9x9 grid
for (let i = 0; i < 81; i++) {
    let cell = document.createElement("input");
    cell.maxLength = 1;

    cell.addEventListener("input", () => {
        let v = cell.value;

        // Valid input → auto move to next cell
        if (/^[1-9]$/.test(v)) {

            let next = cell.nextElementSibling;
            if (next) next.focus(); // Move to next cell

        } else {
            // Invalid input → clear & shake animation
            cell.value = "";
            cell.classList.add("shake");
            setTimeout(() => cell.classList.remove("shake"), 300);
        }
    });

    grid.appendChild(cell);
}


function clearGrid() {
    [...grid.children].forEach(c => c.value = "");
    document.getElementById("timer").innerText = "Solved in: 0 s";
}

function readBoard() {
    let board = [];
    let cells = [...grid.children];

    for (let r = 0; r < 9; r++) {
        board[r] = [];
        for (let c = 0; c < 9; c++) {
            let v = cells[r * 9 + c].value;
            board[r][c] = v === "" ? "." : v;
        }
    }
    return board;
}

function fillBoard(board) {
    let cells = [...grid.children];
    for (let r = 0; r < 9; r++) {
        for (let c = 0; c < 9; c++) {
            cells[r * 9 + c].value = board[r][c] === "." ? "" : board[r][c];
        }
    }
}

async function solvePuzzle() {
    let board = readBoard();
    let start = performance.now();

    let res = await fetch("/solve", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(board)
    });

    let solved = await res.json();
    fillBoard(solved);

    let end = performance.now();
    let timeSeconds = (end - start) / 1000;

document.getElementById("timer").innerText =
    "Solved in: " + timeSeconds.toFixed(2) + " s";

}

async function generatePuzzle() {
    let diff = document.getElementById("difficulty").value;

    let res = await fetch(`/generate/${diff}`);
    let puzzle = await res.json();

    fillBoard(puzzle);
}
