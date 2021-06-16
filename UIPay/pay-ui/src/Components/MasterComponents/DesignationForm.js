import React,{useEffect} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TablePagination from '@material-ui/core/TablePagination';
import TableRow from '@material-ui/core/TableRow';

const columns = [
  { id: 'slno', label: 'SlNo', minWidth: 50 },
  { id: 'id', label: 'Id', minWidth: 50 },
  { id: 'code',label: 'Code',minWidth: 80 },
  { id: 'name',label: 'Name',minWidth: 100 }
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
    var url='http://192.168.43.241:8086/api/designations'
    try
    {
      const response = await fetch(url,requestOptions);
      var data = await response.json();
      if(data!=null && data.length>0)
      {
        for(var i=0;i<data.length;i++)
        {

          rows.push({ slno:i+1, id:data[i].designation_id,code:data[i].designation_code,name:data[i].designation_name});
        }
      }
    }catch(err) 
    {
      alert(err.message);
    }
}

const useStyles = makeStyles({
  root: { width: '100%' },
  container: { maxHeight: 440 },
});

export default function DesignationForm() {
  const classes = useStyles();
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);
  const paperStyle={padding:30,height:'70vh',width:450,margin:"10px auto"}

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };
 
  const handleEachCategory = () => {
    alert("Row index is: ");
  };

  return (
    <Paper style={paperStyle} className={classes.root}>
      <TableContainer className={classes.container}>
        <Table stickyHeader aria-label="sticky table">
          <TableHead>
            <TableRow onclick="handleEachCategory()">
              {columns.map((column) => (
                <TableCell
                  key={column.id}
                  align={column.align}
                  style={{ minWidth: column.minWidth }}
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
    </Paper>
  );
}
