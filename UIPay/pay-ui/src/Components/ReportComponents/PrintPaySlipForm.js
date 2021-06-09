import React, { Component } from 'react';
import { Table, Select , MenuItem, Button,Paper } from '@material-ui/core';

class PrintPaySlipForm extends Component {
    constructor(props) 
    {
        super(props);
        this.state = {
            employeesToDisplay:[],
            employeeId:0
        }
        this.employeesComboChange = this.employeesComboChange.bind(this);
        this.printPaySlip=this.printPaySlip.bind(this);
    }

    componentDidMount(){
        var url='http://192.168.43.241:8086/api/employees';
        this.populateCombos('Employee',url)
    }

    async populateCombos(comboName,url) 
    {
        try
        {
            let initialDataToDisplay = [];

            const requestOptions = {
                crossDomain:true,
                method: 'GET',
                headers: { 'Content-Type': 'application/json',
                            'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
            };
        
            const response = await fetch(url,requestOptions);
            var data = await response.json();
            if(data!=null && data.length>0)
            {
                for(var i=0;i<=data.length-1;i++)
                {
                    if(comboName === 'Employee')
                    {
                        var emp_name= data[i].emp_first_name + " " + data[i]?.emp_middle_name + " " + data[i].emp_last_name;
                        initialDataToDisplay.push(<MenuItem value={data[i].emp_id}>{emp_name}</MenuItem>)
                    }
                }
                if(comboName === 'Employee')
                {
                    this.setState({
                        employeesToDisplay: initialDataToDisplay
                    });
                }
           }
        } catch(err) { alert(err.message); }
    }

    employeesComboChange(event) 
    {
        this.setState({
            employeeId : event.target.value
        });
    }

    async printPaySlip(){

    }
    
    render() {
        const paperStyle={padding:20,height:'25vh',width:600,margin:"40px 100px"}
        const btnStyle={margin:'8px 0'}

        return (
        <div>
            <Paper style={paperStyle} variant="outlined">
            
            <Table>
                <tr><td><label>Employee</label></td>
                    <td>
                    <Select id="employeesCombo" value={this.state.value} onChange={this.employeesComboChange}
                        style={{ border: '1px solid' ,width:'250px' }}>
                        {this.state.employeesToDisplay}
                    </Select>
                    </td>
                    <td><Button type='submit' color='primary' variant='contained' 
                        onClick={() => { this.printPaySlip() }}>Print Pay Slip</Button></td>
                </tr>
            </Table>
            </Paper>
         </div>
        );
    }
}

export default PrintPaySlipForm;