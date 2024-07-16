import { useState, useEffect } from "react";
import SideBar from "./components/SideBar";
import { fetchData } from "./services/api";
import "./index.css";
import DataTable from "./components/DataTable";
import DataTable1 from "./components/DataTable1";

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
			<div className="flex h-screen">
				<SideBar onFilterChange={handleFilterChange} />
				<main className="flex-grow p-4">
					<DataTable />
					{/* <DataTable1 /> */}
				</main>
			</div>
		</div>
	);
}

export default App;
