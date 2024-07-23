import { useState, useEffect } from "react";
import SideBar from "./components/SideBar";
import { fetchData } from "./services/api";
import "./index.css";
import DataTable from "./components/DataTable";
<<<<<<< HEAD
import DataTable1 from "./components/DataTable1";
=======
import { MainContextProvider } from "./contexts/MainContext";
>>>>>>> main

function App() {
	return (
<<<<<<< HEAD
		<div className="flex h-screen">
			<SideBar onFilterChange={handleFilterChange} />
			<main className=" mx-4 my-7 flex-grow items-center justify-center">
				<DataTable1 />
			</main>
		</div>
=======
		<MainContextProvider>
			<div className="flex h-screen">
				<SideBar />
				<main className=" mx-24 my-7 flex-grow items-center justify-center">
					<DataTable />
				</main>
			</div>
		</MainContextProvider>
>>>>>>> main
	);
}

export default App;
