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
		<div className="flex h-screen">
			<SideBar onFilterChange={handleFilterChange} />
			<main className=" mx-4 my-7 flex-grow items-center justify-center">
				<DataTable />
			</main>
		</div>
	);
}

export default App;
