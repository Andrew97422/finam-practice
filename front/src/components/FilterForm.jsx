import React from "react";
import { useForm } from "react-hook-form";
import SearchInput from "./inputs/SearchInput";
import Selector from "./inputs/Selector";
import NumberInput from "./inputs/NumberInput";

const FilterForm = ({ onFilterChange }) => {
	const { register, handleSubmit } = useForm();

	const onSubmit = (data) => {
		onFilterChange(data);
	};

	return (
		<form
			onSubmit={handleSubmit(onSubmit)}
			className=" flex flex-col gap-4"
		>
			<SearchInput {...register("tickername")} />
			<h2 className="text-main">Тип инструмента:</h2>
			<Selector
				{...register("type")}
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
				{...register("sector")}
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
					<span className="text-main w-1/12">От:</span>
					<NumberInput {...register("priceFrom")} className="grow" />
				</div>
				<div className="flex flex-row gap-2">
					<span className="text-main w-1/12">До:</span>
					<NumberInput {...register("priceUpTo")} className="grow" />
				</div>
			</div>
			<h2 className="text-main">Капитализация:</h2>
			<div className="flex flex-col gap-3 w-full">
				<div className="flex flex-row gap-2">
					<span className="text-main w-1/12">От:</span>
					<NumberInput
						{...register("capitalizationFrom")}
						className="grow"
					/>
				</div>
				<div className="flex flex-row gap-2">
					<span className="text-main w-1/12">До:</span>
					<NumberInput
						{...register("capitalizationUpTo")}
						className="grow"
					/>
				</div>
			</div>
			<h2 className="text-main">Средний объем торгов:</h2>
			<div className="flex flex-col gap-3 w-full">
				<div className="flex flex-row gap-2">
					<span className="text-main w-1/12">От:</span>
					<NumberInput {...register("volumeFrom")} />
				</div>
				<div className="flex flex-row gap-2">
					<span className="text-main w-1/12">До:</span>
					<NumberInput {...register("volumeUpTo")} />
				</div>
			</div>
		</form>
	);
};

export default FilterForm;
