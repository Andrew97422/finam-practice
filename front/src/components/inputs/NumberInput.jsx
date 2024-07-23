import { useFormContext } from "react-hook-form";
import ArrowDropUpIcon from "@mui/icons-material/ArrowDropUp";
import ArrowDropDownIcon from "@mui/icons-material/ArrowDropDown";
import { useState } from "react";

const NumberInput = ({ name, min, max, step }) => {
	const { register, setValue, watch, getFieldState } = useFormContext();
	const value = watch(name) || "";

	const intRegExp = /^([1-9][0-9]*|0)?$/;

	const handleInputChange = (e) => {
		const newValue = e.target.value;

		if (!intRegExp.test(newValue)) {
			return; // Не позволяем ввести недопустимые символы
		}

		const numericValue = Number(newValue);
		if (numericValue < min || numericValue > max) {
			setValue(
				name,
				Math.max(Math.min(numericValue, max), min).toString()
			);
		} else {
			setValue(name, newValue);
		}
	};

	const incrementValue = () => {
		const numericValue = Number(value) || 0;
		if (numericValue < max) {
			setValue(name, Math.min(numericValue + step, max).toString());
		}
	};

	const decrementValue = () => {
		const numericValue = Number(value) || 0;
		if (numericValue > min) {
			setValue(name, Math.max(numericValue - step, min).toString());
		}
	};

	return (
		<div className="flex flex-row rounded-lg border-2 border-gray-300 hover:outline-none hover:border-border_primary">
			<input
				{...register(name, {
					validate: (value) => {
						if (!intRegExp.test(value)) {
							return "Invalid format";
						}
						return true;
					},
				})}
				value={value}
				onChange={handleInputChange}
				className="rounded-l-lg grow p-1 bg-white hover:ring-0 focus:outline-none appearance-none"
			/>
			<div className="flex flex-col bg-white rounded-r-lg border-l-2 border-border_primary">
				<button
					type="button"
					className="border-b border-black hover:bg-green-200 active:bg-green-500 rounded-tr-lg"
					onClick={incrementValue}
				>
					<ArrowDropUpIcon className="text-green-700" />
				</button>
				<button
					type="button"
					className="hover:bg-green-200 active:bg-green-500 rounded-br-lg"
					onClick={decrementValue}
				>
					<ArrowDropDownIcon className="text-green-700" />
				</button>
			</div>
		</div>
	);
};

export default NumberInput;
