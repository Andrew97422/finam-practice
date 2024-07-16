import React, { useMemo, useState, useEffect } from "react";
import {
  MRT_Table,
  MRT_TablePagination,
  useMaterialReactTable,
} from "material-react-table";
import { Box } from "@mui/material";
import { MRT_Localization_RU } from "material-react-table/locales/ru";

// Пример данных, обычно такие данные загружают с API
const initialData = [
  {
    name: {
      firstName: "John",
      lastName: "Doe",
    },
    address: "261 Erdman Ford",
    city: "East Daphne",
    state: "Kentucky",
  },
  {
    name: {
      firstName: "Jane",
      lastName: "Doe",
    },
    address: "769 Dominic Grove",
    city: "Columbus",
    state: "Ohio",
  },
  {
    name: {
      firstName: "Joe",
      lastName: "Doe",
    },
    address: "566 Brakus Inlet",
    city: "South Linda",
    state: "West Virginia",
  },
  {
    name: {
      firstName: "Kevin",
      lastName: "Vandy",
    },
    address: "722 Emie Stream",
    city: "Lincoln",
    state: "Nebraska",
  },
  {
    name: {
      firstName: "Joshua",
      lastName: "Rolluffs",
    },
    address: "32188 Larkin Turnpike",
    city: "Charleston",
    state: "South Carolina",
  },
];

const DataTable = () => {
  const [data, setData] = useState([]);

  // Используем useEffect для загрузки данных при монтировании компонента
  useEffect(() => {
    // Задержка для эмуляции загрузки данных
    setTimeout(() => {
      setData(initialData);
    }, 1000);
  }, []);

  // should be memoized or stable
  const columns = useMemo(
    () => [
      {
        accessorKey: "name.firstName", // access nested data with dot notation
        header: "Тикер",
        size: 150,
        enableSorting: false,
        muiTableHeadCellProps: { align: "center" },
      },
      {
        accessorKey: "name.lastName",
        header: "Наименование",
        size: 150,
        enableSorting: false,
        muiTableHeadCellProps: { align: "center" },
      },
      {
        accessorKey: "address", // normal accessorKey
        header: "Цена",
        size: 200,
        muiTableHeadCellProps: { align: "center" },
      },
      {
        accessorKey: "city",
        header: "Капитализация",
        size: 150,
        enableSorting: false,
        muiTableHeadCellProps: { align: "center" },
      },
      {
        accessorKey: "state",
        header: "Ср. объем торгов",
        size: 150,
        enableSorting: false,
        muiTableHeadCellProps: { align: "center" },
      },
    ],
    []
  );

  const table = useMaterialReactTable({
    columns,
    data, // data must be memoized or stable (useState, useMemo, defined outside of this component, etc.)
    enableRowSelection: false, // enable some features
    enableColumnOrdering: false, // enable a feature for all columns
    enableGlobalFilter: false,
    enableDensityToggle: false,
    enableColumnActions: false,
    enableColumnFilters: true,
    enableColumnVisibility: false,
    enableFullScreenToggle: false,
    enablePagination: true,
    initialState: { pagination: { pageSize: 5 } }, // устанавливаем начальный размер страницы
    muiTableHeadCellProps: {
      sx: {
        backgroundColor: "#72d4cc", // Здесь задается цвет фона
        color: "#000", // Здесь задается цвет текста
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
    },
  });

  return (
    <Box>
      <MRT_Table table={table} />
      <MRT_TablePagination table={table} />
    </Box>
  );
};

export default DataTable;
