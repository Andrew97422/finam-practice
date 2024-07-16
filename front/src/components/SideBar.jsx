import FilterForm from "./FilterForm";

const SideBar = ({ onFilterChange }) => {
	return (
		<aside className="h-screen w-fit bg-primary border-r-4 border-border_primary px-4 py-10">
			<FilterForm onFilterChange={onFilterChange} />
		</aside>
	);
};

export default SideBar;
