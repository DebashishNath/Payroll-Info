import React, { PureComponent } from 'react';
import { Table, TextField , Button,Paper } from '@material-ui/core';
import SaveIcon from '@material-ui/icons/Save';
import ClearAllIcon from '@material-ui/icons/ClearAll';
import EditIcon from '@material-ui/icons/Edit';
import MessageBoxForm from '../CommonComponents/MessageBoxForm';

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
            paperHeight:'20vh',
            showMessageBox:false,
            title:'',
            displayMessage:''
        }
        this.updateMasterRecord=this.updateMasterRecord.bind(this);
        this.clearControls=this.clearControls.bind(this);
        this.validateControls=this.validateControls.bind(this);
        this.doEditMasterRecord=this.doEditMasterRecord.bind(this);
    }

    async componentDidMount()
    {
        await document.getElementById("code").focus(); 
        this.ListMasterRecords();
    }

    clearControls()
    {
        this.setState({ id:0 });
        document.getElementById("code").value="";
        document.getElementById("name").value="";
        document.getElementById("code").focus();
    }

    async showMessage(isShowMsgBox,messageBoxtitle,dispMessage,controlName) 
    {
        if(controlName.trim().length > 0)
        {
            document.getElementById(controlName).focus();
        }
        await this.setState({
            showMessageBox:isShowMsgBox,
            title: messageBoxtitle,
            displayMessage: dispMessage
        });
    }

    async validateControls()
    {
        await this.showMessage(false,'','','');

        if(document.getElementById("code").value.trim().length === 0)
        {
            await this.showMessage(true,'Error Information', this.props.LabelCode + ' cannot be blank','code');
            return false;
        }
        if(document.getElementById("name").value.trim().length === 0)
        {
            await this.showMessage(true,'Error Information', this.props.LabelName + ' cannot be blank','name');
            return false;
        }
        return true;
    }

    async updateMasterRecord() 
    {
        if(!await this.validateControls())
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
                await this.showMessage(true,'Save Information',data.message,'code');
                this.clearControls();
                this.ListMasterRecords();
            }
            else
            {
                await this.showMessage(true,'Error Information',data.message,'');
            }
        }
        catch(err)
        { 
            await this.showMessage(true,'Error Information',err.message,'');
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
                    <th>Slno</th><th style={{ display:'none' }}>Id</th><th>Code</th>
                    <th width="300px">Name</th><th></th></tr>);
                for(var i=0;i<=data.length-1;i++)
                {
                    let id=data[i].masterId;
                    let code=data[i].masterCode;
                    let name=data[i].masterName;
                    
                    initialDataToDisplay.push(
                        <tr key={id}>
                        <td>{i+1}</td>
                        <td style={{ display:'none' }}>{id}</td>
                        <td>{code}</td>
                        <td>{name}</td>
                        <td><Button color="primary" variant="contained" size="small" startIcon={<EditIcon/>}
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
                await this.showMessage(true,'Error Information','No Records to display','');
            }
        }catch(err) 
        {
            await this.showMessage(true,'Error Information',err.message,'');
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
        const paperStyle={padding:20,height:this.state.paperHeight,width:500,margin:"20px 100px",
                        border: '5px solid brown', backgroundColor:'white'}
        const btnStyle={margin:'8px 0'}
        const divStyle = {
            border: '5px solid green',
            height: '57vh',
            overflow: 'auto'
          };

        return (
            <Paper style={paperStyle} variant="outlined">
                <div>
                    {this.state.showMessageBox ?
                    <div>
                        <MessageBoxForm title={this.state.title}>
                        {this.state.displayMessage}
                        </MessageBoxForm>
                    </div> : null}
                    <Table>
                    <tr><td><label>{this.props.LabelCode}</label></td>
                        <td><TextField id="code" variant='outlined' required style ={{width: '30%'}} inputProps={{ maxLength: 5 }} size="small"></TextField></td>
                    </tr>
                    <br/>
                    <tr><td><label>{this.props.LabelName}</label></td>
                        <td><TextField id="name" variant='outlined' required inputProps={{ maxLength: 25 }} size="small"></TextField></td>
                    </tr>
                    <tr><td></td><td><Button type='submit' color='primary' variant='contained' 
                            startIcon={<SaveIcon />} style={btnStyle} size='small'
                            onClick={() => { this.updateMasterRecord() }}>Save</Button>&nbsp;
                        <Button color='primary' variant='contained' style={btnStyle} size='small'
                            startIcon={<ClearAllIcon />}
                            onClick={() => { this.clearControls()}}>Reset</Button>
                        </td>
                    </tr>
                    </Table>    
                </div>
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