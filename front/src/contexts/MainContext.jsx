import React, { createContext, useState, useContext } from "react";

const MainContext = createContext();

export const MainContextProvider = ({ children }) => {
	const [filters, setFilters] = useState({
		tickerName: "",
		type: "Акции",
		sector: "Финансы",
		priceFrom: 0,
		priceUpTo: 300000,
		capitalizationFrom: 0,
		capitalizationUpTo: 7000000000000,
		volumeFrom: 0,
		volumeUpTo: 10000000,
	});

	const [offset, setOffset] = useState(0);
	const [limit, setLimit] = useState(5);

	return (
		<MainContext.Provider
			value={{ filters, setFilters, offset, setOffset, limit, setLimit }}
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
