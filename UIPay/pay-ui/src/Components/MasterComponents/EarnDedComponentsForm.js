import React, { PureComponent } from 'react';
import { Table, Button,Paper,Select,MenuItem,TextField } from '@material-ui/core';

class EarnDedComponentsForm extends PureComponent {
    constructor(props) 
    {
        super(props);
        this.state = {
            earnDedTypesToDisplay:[],
            earnDedComponentsToDisplay:[],
            earnDedType:""
        }
        this.doUpdateEarnDedComponent=this.doUpdateEarnDedComponent.bind(this);
        this.earnDedTypeComboChange=this.earnDedTypeComboChange.bind(this);
        this.displayOfEarnDedComponent=this.displayOfEarnDedComponent.bind(this);
    }

    componentDidMount()
    {
        let initialDataToDisplay = [];
        initialDataToDisplay.push(<MenuItem value={"E"}>{"Earning"}</MenuItem>)
        initialDataToDisplay.push(<MenuItem value={"D"}>{"Deduction"}</MenuItem>)
        this.setState({earnDedTypesToDisplay:initialDataToDisplay});
        this.displayEarnDeductions();
    }

    displayOfEarnDedComponent(earnDedId,earnDedCode,earnDedName,earnDedType,earnDedPriority)
    {
        this.setState({ earnDedId : earnDedId });
        document.getElementById("earnDedCode").value=earnDedCode;
        document.getElementById("earnDedName").value=earnDedName;
        this.setState({ earnDedType : earnDedType });
        document.getElementById("earnDedPriority").value=earnDedPriority;
    }

    async doUpdateEarnDedComponent()
    {
        const requestOptions = {
            crossDomain:true,
            method: 'POST',
            headers: { 'Content-Type': 'application/json',
                        'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
            body: JSON.stringify({
                "earn_ded_id": this.state.earnDedId,
                "earn_ded_code": document.getElementById("earnDedCode").value,
                "earn_ded_name" : document.getElementById("earnDedName").value,
                "earn_ded_type": this.state.earnDedType,
                "earn_ded_priority" : document.getElementById("earnDedPriority").value
              })
        };
        
        var url='http://192.168.43.241:8086/api/update_earn_deduction';
        try 
        {
            const response = await fetch(url,requestOptions);
            var data = await response.json();
            
            if (data.code === 0)
            {
                document.getElementById('returnMessage').innerHTML = data.message;
                document.getElementById('returnMessage').style="color:green";
                this.displayEarnDeductions();
            }
            else
            {
              document.getElementById('returnMessage').innerHTML = data.message;
              document.getElementById('returnMessage').style="color:red"
            }
        }catch(err) { alert(err.message); }
    }

    earnDedTypeComboChange(event)
    {
        this.setState({ earnDedType : event.target.value });
    }

    async displayEarnDeductions()
    {
        let initialDataToDisplay = [];
        
        const requestOptions = {
            crossDomain:true,
            method: 'GET',
            headers: { 'Content-Type': 'application/json',
                        'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
        };
        var url='http://192.168.43.241:8086/api/earn_deductions';
        try 
        {
          const response = await fetch(url,requestOptions);
          var data = await response.json();
          
          if(data!=null && data.length>0)
          {
            initialDataToDisplay.push(<tr>
                 <th>Slno</th><th>Id</th><th>Code</th>
                 <th>Name</th><th>Type</th><th>Priority</th><th></th></tr>);
            for(var i=0;i<=data.length-1;i++)
            {
                let earnDedId=data[i].earn_ded_id;
                let earnDedCode=data[i].earn_ded_code;
                let earnDedName=data[i].earn_ded_name;
                let earnDedType=data[i].earn_ded_type;
                let earnDedTypeName='Earning';
                if(data[i].earn_ded_type === "D")
                {
                    earnDedTypeName='Deduction';
                }
                let earnDedPriority=data[i].earn_ded_priority;
                initialDataToDisplay.push(
                    <tr key={earnDedId}>
                    <td>{i+1}</td>
                    <td>{earnDedId}</td>
                    <td>{earnDedCode}</td>
                    <td>{earnDedName}</td>
                    <td>{earnDedTypeName}</td>
                    <td>{earnDedPriority}</td>
                    <td><Button color="primary" variant="contained" onClick={() => 
                        { this.displayOfEarnDedComponent(earnDedId,earnDedCode,earnDedName,earnDedType,earnDedPriority) }}>Edit</Button></td>
                    </tr>);
            }
           }
          else
          {
            initialDataToDisplay.push("No Earning and Deduction Data");
          }
          this.setState({ earnDedComponentsToDisplay: initialDataToDisplay });
        } catch(err) {
            alert(err.message);
        }
    }

    render()
    {
        const paperStyle={padding:30,height:'70vh',width:600,margin:"10px auto",overflow:'auto'}
        return (
            <Paper style={paperStyle} variant="outlined">
                <div>
                    <Table>
                        <tr>
                            <td>Code</td>
                            <td><TextField id="earnDedCode" variant='outlined' style ={{width: '30%'}}></TextField></td>
                            <td colspan="2"><label id = "returnMessage"></label></td>
                        </tr>
                        <br/>
                        <tr>
                            <td>Name</td>
                            <td><TextField id="earnDedName" variant='outlined'></TextField></td>
                            <td>Type</td>
                            <td><Select id="earnDedTypeCombo" value={this.state.earnDedType} onChange={this.earnDedTypeComboChange}
                                    style={{ border: '1px solid' ,width:'150px' }}>
                                    {this.state.earnDedTypesToDisplay}
                                </Select>
                            </td>
                        </tr>
                        <br/>
                        <tr>
                            <td>Priority</td>
                            <td><TextField id="earnDedPriority" variant='outlined' style ={{width: '30%'}}></TextField></td>
                            <td colspan="2"><Button color="primary" variant="contained" onClick={() => { this.doUpdateEarnDedComponent() }}>Update</Button></td>
                        </tr>
                    </Table>
                </div><br/>
                <div>
                    <Table border='1'>
                        {this.state.earnDedComponentsToDisplay}
                    </Table>
                </div>
            </Paper>
        );
    }
}

export default EarnDedComponentsForm;