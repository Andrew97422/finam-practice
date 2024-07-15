import { useMemo } from 'react';
import {
  MaterialReactTable,
  useMaterialReactTable,
} from 'material-react-table';

//nested data is ok, see accessorKeys in ColumnDef below
const data = [
  {
    name: {
      firstName: 'John',
      lastName: 'Doe',
    },
    address: '261 Erdman Ford',
    city: 'East Daphne',
    state: 'Kentucky',
  },
  {
    name: {
      firstName: 'Jane',
      lastName: 'Doe',
    },
    address: '769 Dominic Grove',
    city: 'Columbus',
    state: 'Ohio',
  },
  {
    name: {
      firstName: 'Joe',
      lastName: 'Doe',
    },
    address: '566 Brakus Inlet',
    city: 'South Linda',
    state: 'West Virginia',
  },
  {
    name: {
      firstName: 'Kevin',
      lastName: 'Vandy',
    },
    address: '722 Emie Stream',
    city: 'Lincoln',
    state: 'Nebraska',
  },
  {
    name: {
      firstName: 'Joshua',
      lastName: 'Rolluffs',
    },
    address: '32188 Larkin Turnpike',
    city: 'Charleston',
    state: 'South Carolina',
  },
];

const DataTable = () => {
  //should be memoized or stable
  const columns = useMemo(
    () => [
      {
        accessorKey: 'name.firstName', //access nested data with dot notation
        header: 'Тикер',
        size: 150,
        enableSorting: false,
      },
      {
        accessorKey: 'name.lastName',
        header: 'Наименование',
        size: 150,
        enableSorting: false,
      },
      {
        accessorKey: 'address', //normal accessorKey
        header: 'Цена',
        size: 200,
      },
      {
        accessorKey: 'city',
        header: 'Капитализация',
        size: 150,
        enableSorting: false,
      },
      {
        accessorKey: 'state',
        header: 'Ср. объем торгов',
        size: 150,
        enableSorting: false,
      },
    ],
    [],
  );

  const table = useMaterialReactTable({
    columns,
    data, //data must be memoized or stable (useState, useMemo, defined outside of this component, etc.)
    enableRowSelection: false, //enable some features
    enableColumnOrdering: false, //enable a feature for all columns
    enableGlobalFilter: false, 
    enableDensityToggle: false,
    enableColumnActions: false,
    enableColumnFilters: false,
    enableColumnVisibility: false,
    enableFullScreenToggle: false,
    muiTableProps: {
        sx: {
          border: '1px solid rgba(81, 81, 81, .5)',
          caption: {
            captionSide: 'top',
          },
        },
      },
    muiTableHeadCellProps: {
        sx: {
          border: '1px solid rgba(81, 81, 81, .5)',
        },
      },
      muiTableBodyCellProps: {
        sx: {
          border: '1px solid rgba(81, 81, 81, .5)',
        },
      },
  });

  return <MaterialReactTable table={table} />;
};

export default DataTable;