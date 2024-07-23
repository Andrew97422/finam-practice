import React, { useMemo, useState, useEffect } from "react";
import axios from "axios";
import {
	useMaterialReactTable,
	MRT_Table,
	MRT_TablePagination,
} from "material-react-table";
import { Box } from "@mui/material";
import { MRT_Localization_RU } from "material-react-table/locales/ru";
import FilterListOffIcon from "@mui/icons-material/FilterListOff";
import CustomSortIcon from "./icons/CustomSortIcon.jsx";
import { fetchData } from "../services/api.js";
import { useMainContext } from "../contexts/MainContext.jsx";

const DataTable = () => {
	const [pagination, setPagination] = useState({ pageIndex: 0, pageSize: 5 });
	const [totalRows, setTotalRows] = useState(0);
	const [data, setData] = useState([]);

	const { filters } = useMainContext();

	const loadData = async () => {
		const result = await fetchData(
			filters,
			pagination.pageIndex,
			pagination.pageSize
		);
		console.log(result);
		setData(result.data);
		setTotalRows(result.totalElements);
	};

	useEffect(() => {
		loadData();
	}, [filters, pagination.pageIndex, pagination.pageSize]);

	const columns = useMemo(
		() => [
			{
				accessorKey: "ticker",
				header: "Тикер",
				size: 150,
				enableSorting: true,
				muiTableHeadCellProps: { align: "center" },
			},
			{
				accessorKey: "name",
				header: "Наименование",
				size: 150,
				enableSorting: true,
				muiTableHeadCellProps: { align: "center" },
			},
			{
				accessorKey: "price",
				header: "Цена",
				size: 200,
				muiTableHeadCellProps: { align: "center" },
			},
			{
				accessorKey: "capitalization",
				header: "Капитализация",
				size: 150,
				enableSorting: true,
				muiTableHeadCellProps: { align: "center" },
				Cell: ({ cell }) => {
					const number = cell.getValue();
					return new Intl.NumberFormat("ru-RU", {
						notation: "compact",
						compactDisplay: "short",
						minimumFractionDigits: 0,
						maximumFractionDigits: 3,
					}).format(number);
				},
			},
			{
				accessorKey: "averageTradingVolume",
				header: "Ср. объем торгов",
				size: 150,
				enableSorting: true,
				muiTableHeadCellProps: { align: "center" },
				Cell: ({ cell }) => {
					const number = cell.getValue();
					return new Intl.NumberFormat("ru-RU", {
						notation: "compact",
						compactDisplay: "short",
						minimumFractionDigits: 0,
						maximumFractionDigits: 2,
					}).format(number);
				},
			},
		],
		[]
	);

	const table = useMaterialReactTable({
		columns,
		data: data || [],

		onPaginationChange: setPagination,
		state: {
			pagination,
		},
		rowCount: totalRows, // Убедитесь, что передаете общее количество строк в таблицу

		enableRowSelection: false,
		enableColumnOrdering: false,
		enableGlobalFilter: false,
		enableDensityToggle: false,
		enableColumnActions: false,
		enableColumnFilters: true,
		enableColumnVisibility: false,
		enableFullScreenToggle: false,
		manualPagination: true,
		manualSorting: true,
		muiTableHeadCellProps: {
			sx: {
				backgroundColor: "#ABD5D6",
				color: "#000",
				border: "1px solid rgba(81, 81, 81, .5)",
			},
		},
		muiTableProps: {
			sx: {
				caption: {
					captionSide: "top",
				},
			},
		},
		muiTableBodyCellProps: {
			sx: {
				border: "1px solid rgba(81, 81, 81, .5)",
				textAlign: "center",
			},
		},
		muiTableBodyRowProps: { hover: false },
		localization: MRT_Localization_RU,
		muiPaginationProps: {
			showFirstButton: false,
			showLastButton: false,
			sx: {
				backgroundColor: "#72d4cc",
				display: "flex",
				justifyContent: "flex-end",
			},
		},
		icons: {
			ArrowDownwardIcon: (props) => <CustomSortIcon {...props} />,
			SyncAltIcon: () => <FilterListOffIcon />,
		},
	});

	const handlePageChange = (event, newPage) => {
		setPagination((prev) => ({ ...prev, pageIndex: newPage }));
	};

	const handleRowsPerPageChange = (event) => {
		setPagination((prev) => ({
			...prev,
			pageSize: parseInt(event.target.value, 10),
			pageIndex: 0, // Сброс страницы на 0 при изменении размера
		}));
	};

	return (
		<Box>
			<MRT_Table table={table} />
			<Box
				display="flex"
				justifyContent="flex-end"
				alignItems="center"
				mt={2}
			>
				<MRT_TablePagination
					table={table}
					page={pagination.pageIndex}
					count={Math.ceil(totalRows / pagination.pageSize)}
					onPageChange={handlePageChange}
					rowsPerPage={pagination.pageSize}
					onRowsPerPageChange={handleRowsPerPageChange}
					rowCount={totalRows}
				/>
			</Box>
		</Box>
	);
};

export default DataTable;
