import React from "react";
import { useFormContext } from "react-hook-form";

const NumberInput = ({ name, min = 0, max = 100, step = 1 }) => {
	const { register } = useFormContext();

	const handleInput = (event) => {
		const value = event.target.value;
		if (value !== "" && (Number(value) < min || Number(value) > max)) {
			event.target.value = Math.max(Math.min(Number(value), max), min);
		}
	};

	return (
		<input
			{...register(name)}
			step={step}
			min={min}
			max={max}
			type="number"
			className="rounded-lg grow p-1 mr-32 appearance-none border-2 border-gray-300 hover:ring-0 hover:outline-none hover:border-border_primary focus:outline-none"
			onInput={handleInput}
		/>
	);
};

export default NumberInput;
