import React, { PureComponent } from 'react';
import { Table, TextField , Button,Paper } from '@material-ui/core';
import ListOfRecordsForm from './ListOfRecordsForm';

class MasterForm extends PureComponent
{
    constructor(props) 
    {
        super(props);
        this.state = {
            id: 0
        }
        this.updateMasterRecords=this.updateMasterRecords.bind(this);
        this.clearControls=this.clearControls.bind(this);
        this.validateControls=this.validateControls.bind(this);
    }

    componentDidMount()
    {
        document.getElementById("code").focus(); 
    }

    clearControls()
    {
        this.setState({ id:0 });
        document.getElementById("code").value="";
        document.getElementById("name").value="";
    }

    validateControls()
    {
        if(document.getElementById("code").value.length === 0)
        {
            alert(this.props.LabelCode + " cannot be blank");
            return false;
        }
        if(document.getElementById("name").value.length === 0)
        {
            alert(this.props.LabelName + " cannot be blank");
            return false;
        }
        return true;
    }

    async updateMasterRecords() 
    {
        if(!this.validateControls())
        {
            return;
        }

        var json = '';

        if(this.props.FormName === "Department")
        {
            alert('Inside Json: ' + this.state.id)
            json = 
            {
                "department_id" : this.state.id,
                "department_code" : document.getElementById("code").value,
                "department_name" : document.getElementById("name").value
            };
        }

        var masterParams = JSON.stringify(json);
        alert(masterParams);

        var requestOptions = {
            crossDomain:true,
            method: 'POST',
            headers: { 'Content-Type': 'application/json',
                       'Authorization' : 'Bearer ' +  localStorage.getItem('tokenValue') },
            body: masterParams
        };
      
      var url=this.props.UpdateMasterUrl;
      try 
      {
        const response = await fetch(url,requestOptions);
        var data = await response.json();
        
        if (data.code === 0)
        {
            alert(data.message);
        }
        else
        {
            alert(data.message);
        }
      }
      catch(err) {
        alert(err.message);
      }
    }

    render() {
        const paperStyle={padding:20,height:'100vh',width:600,margin:"40px 100px"}
        const btnStyle={margin:'8px 0'}

        return (
            <Paper style={paperStyle} variant="outlined">
                <div>
                    <Table>
                    <tr><td><label>{this.props.LabelCode}</label></td>
                        <td><TextField id="code" variant='outlined' required style ={{width: '30%'}} inputProps={{ maxLength: 5 }}></TextField></td>
                    </tr>
                    <br/>
                    <tr><td><label>{this.props.LabelName}</label></td>
                        <td><TextField id="name" variant='outlined' required inputProps={{ maxLength: 25 }}></TextField></td>
                    </tr>
                    <tr><td></td><td><Button type='submit' color='primary' variant='contained' style={btnStyle} 
                            onClick={() => { this.updateMasterRecords() }}>Update</Button>&nbsp;
                        <Button color='primary' variant='contained' style={btnStyle} 
                            onClick={() => { this.clearControls()}}>Reset</Button>
                        </td>
                    </tr>
                    </Table>    
                </div><br/>
                <div>
                    <ListOfRecordsForm></ListOfRecordsForm>
                </div>
            </Paper>
        );
    }
}

export default MasterForm;