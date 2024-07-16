const NumberInput = (props) => {
	return (
		<input
			{...props}
			type="number"
			className="rounded-lg grow p-1 appearance-none border-2 border-gray-300 hover:ring-0 hover:outline-none hover:border-border_primary focus:outline-none"
		></input>
	);
};

export default NumberInput;
