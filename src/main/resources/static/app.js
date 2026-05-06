async function generateSql() {
    const question = document.getElementById("question").value;

    const response = await fetch("/api/sql/generate", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ question: question })
    });

    const data = await response.json();

    document.getElementById("output").textContent = data.sql;
}