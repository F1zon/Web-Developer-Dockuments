import React, { useEffect, useState } from "react";

function DockList() {
    const [docks, setDocks] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/docks')
            .then(response => response.json())
            .then(data => setDocks(data))
            .catch(error => console.error('Error fetching dockuments: ', error));
    });

    return (
        <div>
            <h2>Dockuments</h2>
            <ul>
                {docks.map(dock => (
                    <li>{dock}</li>
                ))}
            </ul>
        </div>
    );
}

export default DockList;