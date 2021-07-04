import React, { PureComponent } from 'react';
import { Table, Button,Paper,Select,TextField,MenuItem } from '@material-ui/core';
import moment from 'moment';

class EmployeeLeaveForm extends PureComponent {
    constructor(props) 
    {
        super(props);
        this.setState({
            approveById:0,
            leaveTypeCode:"",
            checked:false
        });
        this.setChecked=this.setChecked.bind(this);
        this.updateEmployeeOfficial=this.updateEmployeeOfficial.bind(this);
        this.leaveTypeCodeChange=this.leaveTypeCodeChange.bind(this);
    }
    componentDidMount()
    {
        alert(localStorage.getItem('LeaveApplicationId'));
    }

    setChecked(){}

    updateEmployeeOfficial(){}

    leaveTypeCodeChange(){}

    render()
    {
        const paperStyle={padding:20,height:'90vh',width:600,margin:"10px auto"}
        const btnStyle={margin:'8px 0'}
   
        return(
            <div>
                <Paper style={paperStyle} variant="outlined">
                <Table>
                    <tr><td><label>Code</label></td>
                        <td><label id ="EmpCode"></label></td>
                        <td><label>Name</label></td>
                        <td><label id ="EmpName"></label></td>
                    </tr><br/>
                    <tr><td><label>App. No</label></td>
                        <td><label id ="AppNo"></label></td>
                        <td><label>App. Date</label></td>
                        <td>
                            <TextField id="AppDate" label="" type="date" defaultValue=""
                            InputLabelProps={{shrink: true,}} />
                        </td>
                    </tr><br/>
                    <tr><td><label>Leave Type</label></td>
                        <td>
                        <Select id="leaveTypesCombo" value={this.state.leaveTypeCode} onChange={this.leaveTypeCodeChange}
                            style={{ border: '1px solid' ,width:'200px' }}>
                            {this.state.leaveTypesToDisplay}
                        </Select>
                        </td><td></td><td></td>
                    </tr><br/>
                    <tr><td><label>From Date</label></td>
                        <td>
                            <TextField id="FromDate" label="" type="date" defaultValue=""
                            InputLabelProps={{shrink: true,}} />
                        </td>
                        <td><label>To Date</label></td>
                        <td>
                            <TextField id="ToDate" label="" type="date" defaultValue=""
                            InputLabelProps={{shrink: true,}} />
                        </td>
                    </tr><br/>
                    <tr><td><label>Approved By</label></td>
                        <td><label><input type="checkbox" defaultChecked={this.state.checked}
                            onChange={() => this.setChecked()} />Approved</label></td>
                        <td>
                        <Select id="approvedByCombo" value={this.state.approveById} onChange={this.approvedByComboChange}
                            style={{ border: '1px solid' ,width:'200px' }}>
                            {this.state.approveByToDisplay}
                        </Select>
                        </td>
                    </tr>
                    <tr><td></td>
                        <td><Button type='submit' color='primary' variant='contained' style={btnStyle} 
                        onClick={() => { this.updateEmployeeOfficial() }}>Save</Button></td>
                    </tr>
                </Table>
                </Paper>
            </div>
        );
    }
}

export default EmployeeLeaveForm;