import React, { useMemo, useState, useEffect } from "react";
import axios from "axios";
import {
  MRT_Table,
  MRT_TablePagination,
  useMaterialReactTable,
} from "material-react-table";
import { Box } from "@mui/material";
import { MRT_Localization_RU } from "material-react-table/locales/ru";
import FilterListOffIcon from "@mui/icons-material/FilterListOff";
import CustomSortIcon from "./icons/CustomSortIcon.jsx";

const fetchData = async (page, pageSize) => {
  // Здесь выполните запрос к вашему API, чтобы получить данные с помощью axios
  // Например:
  const response = await axios.get(`https://api.example.com/data`, {
    params: {
      page: page,
      pageSize: pageSize,
    },
  });
  return response.data;
};

// Пример данных, обычно такие данные загружают с API
const initialData = [
  {
    ticker: "MTSS",
    name: "МТС",
    price: 218.0,
    capitalization: "540460000000",
    volume: "0.5",
  },
  {
    ticker: "ACKO",
    name: "ACKO",
    price: 3252.0,
    capitalization: "-",
    volume: "0.5",
  },
  {
    ticker: "KTSB",
    name: "КСК",
    price: 0.66,
    capitalization: "2470000000",
    volume: "0.5",
  },
  {
    ticker: "NTZL",
    name: "НИТЕЛ",
    price: 40.03,
    capitalization: "-",
    volume: "0.5",
  },
  {
    ticker: "Lorem",
    name: "Imsum",
    price: 0.0,
    capitalization: "Sit",
    volume: "Qwe",
  },
  {
    ticker: "Lorem",
    name: "Imsum",
    price: 0.0,
    capitalization: "Sit",
    volume: "Qwe",
  },
  {
    ticker: "Lorem",
    name: "Imsum",
    price: 0.0,
    capitalization: "Sit",
    volume: "Qwe",
  },
  {
    ticker: "Lorem",
    name: "Imsum",
    price: 0.0,
    capitalization: "Sit",
    volume: "Qwe",
  },
  {
    ticker: "Lorem",
    name: "Imsum",
    price: 0.0,
    capitalization: "Sit",
    volume: "Qwe",
  },
];

const DataTable = () => {
  const [pagination, setPagination] = useState({
    pageIndex: 0,
    pageSize: 5,
  });
  const [totalRows, setTotalRows] = useState(0);

  const [data, setData] = useState([]);

  useEffect(() => {
    const loadData = async () => {
      const result = await fetchData(pagination.pageIndex, pagination.pageSize);
      setData(result.data);
      setTotalRows(result.total); // Установите общее количество строк
    };
    loadData();
  }, [pagination.pageIndex, pagination.pageSize]);

  const columns = useMemo(
    () => [
      {
        accessorKey: "ticker",
        header: "Тикер",
        size: 150,
        enableSorting: false,
        muiTableHeadCellProps: { align: "center" },
      },
      {
        accessorKey: "name",
        header: "Наименование",
        size: 150,
        enableSorting: false,
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
        enableSorting: false,
        muiTableHeadCellProps: { align: "center" },
      },
      {
        accessorKey: "volume",
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
    data,

    onPaginationChange: setPagination,
    state: {
      pagination,
    },

    enableRowSelection: false,
    enableColumnOrdering: false,
    enableGlobalFilter: false,
    enableDensityToggle: false,
    enableColumnActions: false,
    enableColumnFilters: true,
    enableColumnVisibility: false,
    enableFullScreenToggle: false,
    enablePagination: true,
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
        justifyContent: "flex-end", // Выравниваем пагинацию по правой стороне
      },
    },

    icons: {
      ArrowDownwardIcon: (props) => {
        return <CustomSortIcon {...props} />;
      },
      SyncAltIcon: () => <FilterListOffIcon />,
    },
  });

  return (
    <Box>
      <MRT_Table table={table} />
      <Box display="flex" justifyContent="flex-end" alignItems="center" mt={2}>
        <MRT_TablePagination 
			page={pagination.pageIndex}
			onPageChange={(newPage) => setPagination(newPage)}
			rowCount={totalRows} 
			rowsPerPage={pagination.pageSize} 
			table={table} />
      </Box>
    </Box>
  );
};

export default DataTable;
