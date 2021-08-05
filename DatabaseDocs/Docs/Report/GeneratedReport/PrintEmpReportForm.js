import React from 'react'
import { Button,Paper,Table } from '@material-ui/core';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';  
import TableCell from '@material-ui/core/TableCell';  
import TableContainer from '@material-ui/core/TableContainer';  
import TableHead from '@material-ui/core/TableHead';  
import TableRow from '@material-ui/core/TableRow';  
import HelperMethods from '../CommonComponents/HelperMethods';

class PrintEmpReportForm extends React.Component {
    constructor(props) 
    {
        super(props);
        this.state = {  
            employeeData: []  
        } 
        this.printEmployeeList=this.printEmployeeList.bind(this);
    }
    
    async componentDidMount()
    {
    }
  
    printDocument() 
    {  
        const input = document.getElementById('pdfdiv');  
        html2canvas(input)  
            .then((canvas) => {  
                /*var imgWidth = 200;  
                var imgHeight = canvas.height * imgWidth / canvas.width;  
                const imgData = canvas.toDataURL('image/png');  
                const pdf = new jsPDF('p', 'mm', 'a4')  
                var position = 0;  
                
                pdf.addImage(imgData, 'JPEG', 0, position, imgWidth, imgHeight); */
                const pdf = new jsPDF('p', 'mm', 'a4')  
                pdf.save("employee_list.pdf");  
            }); 
    } 

    async printEmployeeList()
    {
        var initialDataToDisplay=[];
        const requestOptions = {
            crossDomain:true,
            method: 'GET',
            headers: { 'Content-Type': 'application/json',
                        'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
        };
        var url=HelperMethods.GetServerIP() + 'api/employees';
        try 
        {
            const response = await fetch(url,requestOptions);
            var data=await response.json();
            if(data!=null && data.length>0)
            {
                initialDataToDisplay.push(<TableHead><TableRow>
                 <TableCell align="right">Slno</TableCell>
                 <TableCell align="right">Code</TableCell>
                 <TableCell align="right">Name</TableCell>
                 <TableCell align="right">Gender</TableCell>
                 <TableCell align="right">Department</TableCell>
                 <TableCell align="right">Designation</TableCell>
                 </TableRow></TableHead>);
                for(var i=0;i<=data.length-1;i++)
                {
                    let gender='Male';
                    if(data[i].gender === "F"){
                        gender='Female';
                    }
                    initialDataToDisplay.push(
                        <TableRow key={data[i].emp_id}>  
                            <TableCell align="center">{i+1}</TableCell>
                            <TableCell align="right">{data[i].emp_code}</TableCell> 
                            <TableCell align="right">{data[i].emp_first_name + ' ' + data[i]?.emp_middle_name + ' ' + data[i].emp_last_name}</TableCell>  
                            <TableCell align="right">{gender}</TableCell>  
                            <TableCell align="right">{data[i]?.department?.department_name}</TableCell>  
                            <TableCell align="right">{data[i]?.designation?.designation_name}</TableCell>  
                        </TableRow>)
                }
                await this.setState({
                    employeeData: initialDataToDisplay
                });
                this.printDocument();
                 alert('Report Generated Successfully');
            }
        }catch(err)
        {
            alert(err.message);
        }
        //await this.showMessage(true,'Report Information','Report Generated Successfully','');
    }

    render() { 
        const paperStyle={padding:20,height:550,width:800,margin:"40px 100px",border: '5px solid brown'}
        return(
            <Paper style={paperStyle} variant="outlined">
             <div>  
                <TableContainer id="pdfdiv" className="txt" style={{height:500,width:790}} component={Paper}>  
                <Table stickyHeader aria-label="sticky table">  
                    {this.state.employeeData}
                </Table> 
                </TableContainer>
            </div><br/>
            <div>
                <Table>
                    <tr>
                        <td><Button color='primary' variant='contained' onClick={() => { this.printEmployeeList() }}>Print Employee List</Button></td>
                    </tr>
                </Table>
            </div>
            </Paper>
        );
    }
}

export default PrintEmpReportForm;