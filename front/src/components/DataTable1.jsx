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

const fetchData = async (pageIndex, pageSize, sorting) => {
  const response = await axios.get(
    `https://jsonplaceholder.typicode.com/todos`
  );
  const filteredData = response.data.filter(
    (elem) =>
      elem.id > pageIndex * pageSize && elem.id <= (pageIndex + 1) * pageSize
  );
  console.log(pageIndex)
  console.log(pageSize)
  console.log(sorting)
  return {
    data: filteredData,
    total: 200,
  };
};

const DataTable1 = () => {
  const [pagination, setPagination] = useState({
    pageIndex: 0,   
    pageSize: 5,
  });
  const [totalRows, setTotalRows] = useState(0);
  const [data, setData] = useState([]);
  const [sorting, setSorting] = useState([]);

  useEffect(() => {
    const loadData = async () => {
      const result = await fetchData(pagination.pageIndex, pagination.pageSize, sorting);
      setData(result.data);
      setTotalRows(result.total);
    };
    loadData();
  }, [pagination.pageIndex, pagination.pageSize, sorting]);

  const columns = useMemo(
    () => [
      {
        accessorKey: "userId",
        header: "Тикер",
        size: 150,
        enableSorting: false,
        muiTableHeadCellProps: { align: "center" },
      },
      {
        accessorKey: "id",
        header: "Наименование",
        size: 150,
        enableSorting: false,
        muiTableHeadCellProps: { align: "center" },
      },
      {
        accessorKey: "title",
        header: "Цена",
        size: 200,
        muiTableHeadCellProps: { align: "center" },
      },
      {
        accessorKey: "completed",
        header: "Капитализация",
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
    onSortingChange: setSorting,

    // onPaginationChange: (pagination) => {
    //   setPagination((prev) => ({
    //     ...prev,
       
    //   }));
    // },

    state: {
      pagination,
      sorting,
    },

    rowCount: totalRows,
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
    //enablePagination: true,
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
          // page={pagination.pageIndex}
          //count={Math.ceil(totalRows / pagination.pageSize)}
          //   onPageChange={(_, newPage) =>
          //     setPagination(prev => ({ ...prev, pageIndex: newPage }))
          //   }
          //rowsPerPage={pagination.pageSize}
          //   onRowsPerPageChange={(event) =>
          //     setPagination(prev => ({ ...prev, pageSize: parseInt(event.target.value, 10) }))
          //   }
          //rowCount={totalRows}
          table={table}
        />
      </Box>
    </Box>
  );
};

export default DataTable1;
