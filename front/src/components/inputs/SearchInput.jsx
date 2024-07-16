import SearchIcon from "@mui/icons-material/Search";

const SearchInput = (props) => {
	return (
		<div className="relative">
			<input
				type="text"
				{...props}
				id="search"
				class="block rounded-lg pl-2.5 pr-10 pb-2.5 pt-5 w-full font-roboto text-sm text-gray-900 bg-white  border-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-border_primary peer hover:ring-0 hover:outline-none hover:border-border_primary"
				placeholder=" "
			/>
			<label
				htmlFor="search"
				class=" absolute font-roboto  text-sm text-gray-500  duration-300 transform -translate-y-4 scale-75 top-4 z-10 origin-[0] start-2.5 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-4 rtl:peer-focus:translate-x-1/4 rtl:peer-focus:left-auto"
			>
				Тикер или наименование
			</label>
			<div class="absolute inset-y-0 end-1 flex items-center ps-3 pointer-events-none">
				<SearchIcon />
			</div>
		</div>
	);
};

export default SearchInput;
