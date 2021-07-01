import React, { PureComponent } from 'react';
import { Table, TextField , Button,Paper } from '@material-ui/core';

class MasterForm extends PureComponent
{
    constructor(props) 
    {
        super(props);
        this.state = 
        { 
            id: 0,
            ListRecords:[],
            showMasterRecords: false,
            paperHeight:'20vh'
        }
        this.updateMasterRecord=this.updateMasterRecord.bind(this);
        this.clearControls=this.clearControls.bind(this);
        this.validateControls=this.validateControls.bind(this);
        this.doEditMasterRecord=this.doEditMasterRecord.bind(this);
    }

    componentDidMount()
    {
        document.getElementById("code").focus(); 
        this.ListMasterRecords();
    }

    clearControls()
    {
        this.setState({ id:0 });
        document.getElementById("code").value="";
        document.getElementById("name").value="";
    }

    validateControls()
    {
        if(document.getElementById("code").value.trim().length === 0)
        {
            alert(this.props.LabelCode + " cannot be blank");
            document.getElementById("code").focus();
            return false;
        }
        if(document.getElementById("name").value.trim().length === 0)
        {
            alert(this.props.LabelName + " cannot be blank");
            document.getElementById("name").focus();
            return false;
        }
        return true;
    }

    async updateMasterRecord() 
    {
        if(!this.validateControls())
        {
            return;
        }

        var json = '';

        if(this.props.FormName === "Department")
        {
            json = 
            {
                "department_id" : this.state.id,
                "department_code" : document.getElementById("code").value,
                "department_name" : document.getElementById("name").value
            };
        }

        if(this.props.FormName === "Category")
        {
            json = 
            {
                "category_id" : this.state.id,
                "category_code" : document.getElementById("code").value,
                "category_name" : document.getElementById("name").value
            };
        }

        if(this.props.FormName === "Designation")
        {
            json = 
            {
                "designation_id" : this.state.id,
                "designation_code" : document.getElementById("code").value,
                "designation_name" : document.getElementById("name").value
            };
        }

        var masterParams = JSON.stringify(json);
        
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
            this.ListMasterRecords();
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

    async ListMasterRecords()
    {
        let initialDataToDisplay=[];
        this.setState ({ 
            ListRecords: []
        });
        const requestOptions = 
        {
            crossDomain:true,
            method: 'GET',
            headers: { 'Content-Type': 'application/json',
                        'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
        };
        var url=this.props.ListMasterUrl;
        try
        {
            const response = await fetch(url,requestOptions);
            var data = await response.json();
            
            if(data!=null && data.length>0)
            {
                initialDataToDisplay.push(<tr>
                    <th>Slno</th><th>Id</th><th>Code</th>
                    <th>Name</th><th></th></tr>);
                for(var i=0;i<=data.length-1;i++)
                {
                    let id=data[i].masterId;
                    let code=data[i].masterCode;
                    let name=data[i].masterName;
                    
                    initialDataToDisplay.push(
                        <tr key={id}>
                        <td>{i+1}</td>
                        <td>{id}</td>
                        <td>{code}</td>
                        <td>{name}</td>
                        <td><Button color="primary" variant="contained" 
                            onClick={() => { this.doEditMasterRecord(id,code,name) }}>Edit</Button></td>
                        </tr>);
                }
                this.setState ({ 
                    paperHeight:'80vh',
                    showMasterRecords:true,
                    ListRecords: initialDataToDisplay
                });
            }
            else
            {
                this.setState ({ 
                    paperHeight:'20vh',
                    showMasterRecords:false });
                alert("No Records to display");
            }
            
        }catch(err) 
        {
            alert(err.message);
        }
    }

    doEditMasterRecord(masterId,masterCode,masterName)
    {
        this.setState({ id:masterId });
        document.getElementById("code").value=masterCode;
        document.getElementById("name").value=masterName;
        document.getElementById("code").focus();
    }

    render() {
        const paperStyle={padding:20,height:this.state.paperHeight,width:500,margin:"40px 100px",border: '5px solid brown'}
        const btnStyle={margin:'8px 0'}
        const divStyle = {
            border: '5px solid green',
            height: '50vh',
            overflow: 'auto'
          };

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
                            onClick={() => { this.updateMasterRecord() }}>Update</Button>&nbsp;
                        <Button color='primary' variant='contained' style={btnStyle} 
                            onClick={() => { this.clearControls()}}>Reset</Button>
                        </td>
                    </tr>
                    </Table>    
                </div><br/>
                { this.state.showMasterRecords ?
                    <div style={divStyle}>
                        <Table border='1'>
                            {this.state.ListRecords}
                        </Table>
                    </div> : null
                }
            </Paper>
        );
    }
}

export default MasterForm;