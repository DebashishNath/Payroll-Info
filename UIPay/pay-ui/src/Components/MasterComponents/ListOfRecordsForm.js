import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TablePagination from '@material-ui/core/TablePagination';
import TableRow from '@material-ui/core/TableRow';
import { Button } from '@material-ui/core';

const columns = [
  { id: 'slno', label: 'SlNo', minWidth: 50 },
  { id: 'id',   label: 'Id', minWidth: 50 },
  { id: 'code', label: 'Code',minWidth: 80 },
  { id: 'name', label: 'Name',minWidth: 100 },
  { id: 'edit', label: '',minWidth: 80}
];

let rows = [];
createData();


async function createData() 
{
  const requestOptions = {
    crossDomain:true,
    method: 'GET',
    headers: { 'Content-Type': 'application/json',
                'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
    };
    var url='http://192.168.43.241:8086/api/departments'
    try
    {
      const response = await fetch(url,requestOptions);
      var data = await response.json();
      if(data!=null && data.length>0)
      {
        for(var i=0;i<data.length;i++)
        {
            let dataId=data[i].department_id;
            let dataCode=data[i].department_code;
            let dataName=data[i].department_name;
            rows.push({ slno:i+1, id:dataId,code:dataCode,name:dataName,
              edit:<Button color="primary" variant="contained" 
                    onClick={() => { doEditOfMaster(dataId,dataCode,dataName) }}>Edit</Button>});
        }
      }
    }catch(err) 
    {
      alert(err.message);
    }
}

const doEditOfMaster=(dataId,dataCode,dataName)=>
{ 
    alert('Data Id: ' + dataId + ',Data Code: ' + dataCode + ',Data Name: ' + dataName);
}

const useStyles = makeStyles({
  root: { width: '100%' },
  container: { maxHeight: 440 },
});

export default function ListOfRecordsForm() {
  const classes = useStyles();
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);
  
  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };
  
  return (
    <div className={classes.root}>
      <TableContainer className={classes.container}>
        <Table stickyHeader aria-label="sticky table">
          <TableHead>
            <TableRow>
              {
              columns.map((column) => (
                <TableCell
                  key={column.id}
                  align={column.align}
                  style={{ minWidth: column.minWidth }
                }
                 >
                 {column.label}
                </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((row) => {
              return (
                <TableRow hover role="checkbox" tabIndex={-1} key={row.code}>
                  {columns.map((column) => {
                    const value = row[column.id];
                    return (
                      <TableCell key={column.id} align={column.align}>
                        {column.format && typeof value === 'number' ? column.format(value) : value}
                      </TableCell>
                    );
                  })}
                </TableRow>
              );
            })}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[10, 25, 100]}
        component="div"
        count={rows.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onChangePage={handleChangePage}
        onChangeRowsPerPage={handleChangeRowsPerPage}
      />
    </div>
  );
}
