import FilterForm from "./FilterForm";

const SideBar = ({ onFilterChange }) => {
	return (
		<aside className="h-full min-w-60 bg-primary border-r-4 border-border_primary px-4 py-10 overflow-auto">
			<FilterForm onFilterChange={onFilterChange} />
		</aside>
	);
};

export default SideBar;
