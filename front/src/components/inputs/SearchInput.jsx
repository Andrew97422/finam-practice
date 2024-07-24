import SearchIcon from "@mui/icons-material/Search";
import { useFormContext } from "react-hook-form";
import { fetchSuggestions } from "../../services/api";
import { useMainContext } from "../../contexts/MainContext";
import { useState } from "react";

const SearchInput = ({ name }) => {
	const [suggestions, setSuggestions] = useState([]);
	const { register, setValue } = useFormContext();
	const { filters, setFilters } = useMainContext();

	const handleInput = (event) => {
		setSuggestions([]);
		const newValue = event.target.value;
		if (newValue) {
			const loadSuggestions = async () => {
				const result = await fetchSuggestions({
					...filters,
					tickerName: newValue,
				});

				setSuggestions(result.data);
			};
			loadSuggestions();
		} else {
			setSuggestions([]);
		}
	};

	return (
		<div className="relative flex flex-row">
			<div className="relative grow">
				<input
					type="text"
					{...register(name)}
					className="block rounded-l-lg rounded-y-lg pl-2.5 pr-10 pb-2.5 pt-5 w-full font-roboto text-sm text-gray-900 bg-white border-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-border_primary peer hover:ring-0 hover:outline-none hover:border-border_primary"
					placeholder=" "
					onInput={handleInput}
					onFocus={handleInput}
				/>
				<label
					htmlFor="search"
					className="absolute font-roboto hover:cursor-text text-sm text-gray-500 duration-300 transform -translate-y-4 scale-75 top-4 z-10 origin-[0] start-2.5 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-4 rtl:peer-focus:translate-x-1/4 rtl:peer-focus:left-auto"
				>
					Тикер или наименование
				</label>
				{suggestions.length > 0 && (
					<ul className="absolute z-10 w-full bg-white border border-gray-300 rounded-lg shadow-lg mt-1">
						{suggestions.map((suggestion, index) => (
							<li
								key={index}
								className="px-4 py-2 cursor-pointer hover:bg-gray-200"
								onClick={() => {
									// Устанавливаем значение в поле ввода при выборе подсказки
									setValue(name, suggestion);
									setFilters({
										...filters,
										tickerName: suggestion,
									});
									setSuggestions([]);
								}}
							>
								{suggestion}
							</li>
						))}
					</ul>
				)}
			</div>
			<button
				type="submit"
				className="bg-gray-300 rounded-r-lg hover:bg-gray-400 active:bg-gray-500 w-12"
			>
				<SearchIcon />
			</button>
		</div>
	);
};

export default SearchInput;
