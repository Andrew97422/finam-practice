import { useState, useEffect } from "react";
import SideBar from "./components/SideBar";
import { fetchData } from "./services/api";
import "./index.css";
import DataTable from "./components/DataTable";
import { MainContextProvider } from "./contexts/MainContext";

function App() {
	const [data, setData] = useState([]);
	const [filters, setFilters] = useState({
		tickerName: "",
		type: "Акции",
		sector: "Финансы",
		priceFrom: "0",
		priceUpTo: "300000",
		capitalizationFrom: "0",
		capitalizationUpTo: "7000000000000",
		volumeFrom: "0",
		volumeUpTo: "100000000",
	});

	useEffect(() => {
		const loadData = async () => {
			const result = await fetchData(filters, 0, 1);
			console.log(result);
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
		<MainContextProvider>
			<div className="flex h-screen">
				<SideBar onFilterChange={handleFilterChange} />
				<main className=" mx-24 my-7 flex-grow items-center justify-center">
					<DataTable />
				</main>
			</div>
		</MainContextProvider>
	);
}

export default App;
