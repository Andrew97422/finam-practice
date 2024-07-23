import React, { createContext, useState, useContext } from "react";

const MainContext = createContext();

export const MainContextProvider = ({ children }) => {
	const [filters, setFilters] = useState({
		tickerName: "",
		type: "Акции",
		sector: "Финансы",
		priceFrom: 0,
		priceUpTo: 3000000, //2953292 - максимальное в БД
		capitalizationFrom: 0,
		capitalizationUpTo: 7000000000000, // 6883645978240 - максимальное в БД
		volumeFrom: 0,
		volumeUpTo: 1250000000, // 1236600886 - максимальное в БД
		sortBy: "price",
		sortOrder: "desc",
	});

	const [isSidebarOpen, setSidebarOpen] = useState(true);

	return (
		<MainContext.Provider
			value={{
				filters,
				setFilters,
				isSidebarOpen,
				setSidebarOpen,
			}}
		>
			{children}
		</MainContext.Provider>
	);
};

export function useMainContext() {
	const context = useContext(MainContext);
	if (!context) {
		throw new Error(
			"useMainContext должен использоваться внутри MainContextProvider"
		);
	}
	return context;
}
