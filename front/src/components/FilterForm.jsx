import React from "react";
import { useForm, FormProvider } from "react-hook-form";
import SearchInput from "./inputs/SearchInput";
import Selector from "./inputs/Selector";
import NumberInput from "./inputs/NumberInput";
import { useMainContext } from "../contexts/MainContext";

const FilterForm = () => {
	const methods = useForm({
		mode: "onChange",
		defaultValues: {
			tickerName: "",
			type: "Акции",
			sector: "Финансы",
			priceFrom: 0,
			priceUpTo: 300000,
			capitalizationFrom: 0,
			capitalizationUpTo: 7000000000000,
			volumeFrom: 0,
			volumeUpTo: 1000000,
		},
	});

	const { setFilters } = useMainContext();
	const onSubmit = (data) => {
		setFilters(data);
	};

	return (
		<FormProvider {...methods}>
			<form
				className="flex flex-col gap-4 h-full"
				onSubmit={methods.handleSubmit(onSubmit)}
			>
				<SearchInput name="tickerName" />
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
						<NumberInput name="priceFrom" min={0} />
					</div>
					<div className="flex flex-row gap-2">
						<span className="text-main w-1/6">До:</span>
						<NumberInput name="priceUpTo" min={0} />
					</div>
				</div>
				<h2 className="text-main">Капитализация:</h2>
				<div className="flex flex-col gap-3 w-full">
					<div className="flex flex-row gap-2">
						<span className="text-main w-1/6">От:</span>
						<NumberInput name="capitalizationFrom" min={0} />
					</div>
					<div className="flex flex-row gap-2">
						<span className="text-main w-1/6">До:</span>
						<NumberInput name="capitalizationUpTo" min={0} />
					</div>
				</div>
				<h2 className="text-main">Средний объем торгов:</h2>
				<div className="flex flex-col gap-3 w-full">
					<div className="flex flex-row gap-2">
						<span className="text-main w-1/6">От:</span>
						<NumberInput name="volumeFrom" min={0} />
					</div>
					<div className="flex flex-row gap-2">
						<span className="text-main w-1/6">До:</span>
						<NumberInput name="volumeUpTo" min={0} />
					</div>
				</div>
				<div className="flex flex-row justify-around">
					<button
						type="submit"
						className="rounded-xl bg-[#489DA5] hover:bg-[#397c82] active:bg-[#2b5f64] text-white font-roboto px-4 py-2"
					>
						Применить
					</button>
					<button
						type="button"
						className="rounded-xl bg-[#D3DDDE] hover:bg-[#a8b0b1] active:bg-[#7a8080] font-roboto px-4 py-2"
						onClick={() => {
							methods.reset();
						}}
					>
						Сбросить
					</button>
				</div>
				{/* <DevTool control={methods.control} /> */}
			</form>
		</FormProvider>
	);
};

export default FilterForm;
