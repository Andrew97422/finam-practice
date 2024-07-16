import React, { useEffect, useRef, useCallback } from "react";
import { useForm, FormProvider } from "react-hook-form";
import { DevTool } from "@hookform/devtools";
import SearchInput from "./inputs/SearchInput";
import Selector from "./inputs/Selector";
import NumberInput from "./inputs/NumberInput";

const FilterForm = ({ onFilterChange }) => {
	const methods = useForm({
		mode: "onChange",
		defaultValues: {
			tickername: "",
			type: "Акции",
			sector: "Финансы",
			priceFrom: "0",
			priceUpTo: "300000",
			capitalizationFrom: "0",
			capitalizationUpTo: "7000000000000",
			volumeFrom: "0",
			volumeUpTo: "1",
		},
	});

	const { handleSubmit, watch } = methods;
	const formValues = watch();
	const debounceRef = useRef(null);
	const prevFormValuesRef = useRef(formValues);

	const debouncedSubmit = useCallback(
		(data) => {
			// Debounce function
			if (debounceRef.current) {
				clearTimeout(debounceRef.current);
			}
			debounceRef.current = setTimeout(() => {
				onFilterChange(data);
			}, 300);
		},
		[onFilterChange]
	);

	useEffect(() => {
		const hasFormChanged =
			JSON.stringify(prevFormValuesRef.current) !==
			JSON.stringify(formValues);

		if (hasFormChanged) {
			prevFormValuesRef.current = formValues;
			handleSubmit(debouncedSubmit)();
		}
	}, [formValues, handleSubmit, debouncedSubmit]);

	return (
		<FormProvider {...methods}>
			<form className="flex flex-col gap-4 h-full">
				<SearchInput name="tickername" />
				<h2 className="text-main">Тип инструмента:</h2>
				<Selector
					name="type"
					optionsData={[
						{ value: "Акции", text: "Акции" },
						{ value: "Индексы", text: "Индексы" },
						{ value: "Фьючерсы", text: "Фьючерсы" },
						{ value: "Валюты", text: "Валюты" },
						{ value: "Фонды", text: "Фонды" },
						{ value: "Облигации", text: "Облигации" },
					]}
				/>
				<h2 className="text-main">Сектор:</h2>
				<Selector
					name="sector"
					optionsData={[
						{ value: "Финансы", text: "Финансы" },
						{ value: "Энергетика", text: "Энергетика" },
						{ value: "Материалы", text: "Материалы" },
						{
							value: "Потребительские товары массового спроса",
							text: "Потребительские товары массового спроса",
						},
						{ value: "Услуги связи", text: "Услуги связи" },
						{
							value: "Неосновные потребительские товары",
							text: "Неосновные потребительские товары",
						},
						{ value: "Промышленность", text: "Промышленность" },
						{ value: "Недвижимость", text: "Недвижимость" },
						{
							value: "Коммунальные услуги",
							text: "Коммунальные услуги",
						},
						{
							value: "Информационные технологии",
							text: "Информационные технологии",
						},
						{
							value: "Здравоохранение",
							text: "Здравоохранение",
						},
					]}
				/>
				<h2 className="text-main">Цена:</h2>
				<div className="flex flex-col gap-3 w-full">
					<div className="flex flex-row gap-2">
						<span className="text-main w-1/6">От:</span>
						<NumberInput name="priceFrom" min={0} max={300000} />
					</div>
					<div className="flex flex-row gap-2">
						<span className="text-main w-1/6">До:</span>
						<NumberInput name="priceUpTo" min={0} max={300000} />
					</div>
				</div>
				<h2 className="text-main">Капитализация:</h2>
				<div className="flex flex-col gap-3 w-full">
					<div className="flex flex-row gap-2">
						<span className="text-main w-1/6">От:</span>
						<NumberInput
							name="capitalizationFrom"
							min={0}
							max={7000000000000}
						/>
					</div>
					<div className="flex flex-row gap-2">
						<span className="text-main w-1/6">До:</span>
						<NumberInput
							name="capitalizationUpTo"
							min={0}
							max={7000000000000}
						/>
					</div>
				</div>
				<h2 className="text-main">Средний объем торгов:</h2>
				<div className="flex flex-col gap-3 w-full">
					<div className="flex flex-row gap-2">
						<span className="text-main w-1/6">От:</span>
						<NumberInput
							name="volumeFrom"
							min={0}
							max={1}
							step={0.01}
						/>
					</div>
					<div className="flex flex-row gap-2">
						<span className="text-main w-1/6">До:</span>
						<NumberInput
							name="volumeUpTo"
							min={0}
							max={1}
							step={0.01}
						/>
					</div>
				</div>
				{/* <DevTool control={methods.control} /> */}
			</form>
		</FormProvider>
	);
};

export default FilterForm;
