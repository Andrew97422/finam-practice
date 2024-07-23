import { useFormContext } from "react-hook-form";

const NumberInput = ({ name, min, max, step }) => {
	const { register } = useFormContext();

	const handleInput = (event) => {
		const value = event.target.value;
		if (value !== "" && (Number(value) < min || Number(value) > max)) {
			event.target.value = Math.max(Math.min(Number(value), max), min);
		}
	};

	return (
		<div className="">
			<input
				{...register(name)}
				step={step}
				min={min}
				max={max}
				className="rounded-lg grow p-1 mr-28 bg-white  border-2 border-gray-300 hover:ring-0 hover:outline-none hover:border-border_primary focus:outline-none appearance-none"
				onInput={handleInput}
			/>
		</div>
	);
};

export default NumberInput;
