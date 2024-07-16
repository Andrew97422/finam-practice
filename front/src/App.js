import { useState, useEffect } from "react";
import SideBar from "./components/SideBar";
import { fetchData } from "./services/api";
import "./index.css";

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
		<div>
			<p>{JSON.stringify(data)}</p>
			<SideBar onFilterChange={handleFilterChange} />
		</div>
	);
}

export default App;
