import { useState, useEffect } from "react";
import SideBar from "./components/SideBar";
import { fetchData } from "./services/api";
import "./index.css";
import DataTable from "./components/DataTable";

function App() {
	const [data, setData] = useState([]);
	const [filters, setFilters] = useState({});

	useEffect(() => {
		const loadData = async () => {
			const result = await fetchData(filters);
			setData(result);
		};
		loadData();
	}, [filters]);

	const handleFilterChange = (newFilters) => {
		// Avoid unnecessary state updates
		setFilters((prevFilters) => {
			if (JSON.stringify(prevFilters) !== JSON.stringify(newFilters)) {
				console.log(JSON.stringify(newFilters, "", 4));
				return newFilters;	
			}
			return prevFilters;
		});
	};

	return (
		<div className="App">
			<header className="App-header">
				<img src={logo} className="App-logo" alt="logo" />
				<p>
					Edit <code>src/App.js</code> and save to reload.
				</p>
				<a
					className="App-link"
					href="https://reactjs.org"
					target="_blank"
					rel="noopener noreferrer"
				>
					Learn React
				</a>
			</header>
		</div>
	);
}

export default App;
