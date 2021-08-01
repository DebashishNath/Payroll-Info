import React, { Component } from 'react';
import { TextField, Table, Select , MenuItem, Button,Paper } from '@material-ui/core';
import MessageBoxForm from '../CommonComponents/MessageBoxForm';
import HelperMethods from '../CommonComponents/HelperMethods';

class PrintPTAXMonthlyForm extends Component {
    constructor(props) 
    {
        super(props);
        this.state = {
            monthsToDisplay:[],
            ptaxToDisplay:[],
            monthId:0,
            showPTax: false,
            paperHeight:'10vh',
            showMessageBox:false,
            title:'',
            displayMessage:''
        }
        this.monthsComboChange=this.monthsComboChange.bind(this);
        this.printPTAX=this.printPTAX.bind(this);
    }

    async componentDidMount(){
        this.populateMonths();
        document.getElementById("year").focus();
    }

    populateMonths()
    {
      let initialDataToDisplay = [];
  
      initialDataToDisplay.push(<MenuItem value={1}>January</MenuItem>);
      initialDataToDisplay.push(<MenuItem value={2}>February</MenuItem>);
      initialDataToDisplay.push(<MenuItem value={3}>March</MenuItem>);
      initialDataToDisplay.push(<MenuItem value={4}>April</MenuItem>);
      initialDataToDisplay.push(<MenuItem value={5}>May</MenuItem>);
      initialDataToDisplay.push(<MenuItem value={6}>June</MenuItem>);
      initialDataToDisplay.push(<MenuItem value={7}>July</MenuItem>);
      initialDataToDisplay.push(<MenuItem value={8}>August</MenuItem>);
      initialDataToDisplay.push(<MenuItem value={9}>September</MenuItem>);
      initialDataToDisplay.push(<MenuItem value={10}>October</MenuItem>);
      initialDataToDisplay.push(<MenuItem value={11}>November</MenuItem>);
      initialDataToDisplay.push(<MenuItem value={12}>December</MenuItem>);
  
      this.setState({
        monthsToDisplay: initialDataToDisplay
      });
    }
  
    monthsComboChange(event) 
    {
      this.setState({
        monthId : event.target.value
      });
    }
    
    async validateControls()
    {
        if(document.getElementById("year").value.trim().length === 0)
        {
            document.getElementById("year").focus();
            await this.setState({
                showMessageBox:true,
                title:'Error Information',
                displayMessage: 'Enter Year for PTAX'
            });
            return false;
        }
        if(this.state.monthId === 0)
        {
            document.getElementById("monthsCombo").focus();
            await this.setState({
                showMessageBox:true,
                title:'Error Information',
                displayMessage: 'Select Month for PTAX'
            });
            return false;
        }
        return true;
    }

    async printPTAX()
    {
        await this.setState({
            showMessageBox:false,
            title:'',
            displayMessage: ''
        });

        if(!await this.validateControls())
        {
            return;
        }

        let initialDataToDisplay=[];
        try
        {
            const requestOptions = 
            {
                crossDomain:true,
                method: 'GET',
                headers: { 'Content-Type': 'application/json',
                           'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') }
            };
            
            var monthId=this.state.monthId;
            var year=document.getElementById("year").value;
            
            var url= HelperMethods.GetServerIP() + 'api/ptaxmonthly/'  + monthId + '/' + year;
            
            const resp = await fetch(url,requestOptions);
            var data = await resp.json();
            if(data!=null && data.length > 0)
            {
                initialDataToDisplay.push(<tr>
                    <th>Slno</th>
                    <th>Slab</th><th>Rate of Tax</th>
                    <th>No of Employees</th><th>Tax Amount</th>
                    </tr>);
                for (var i = 0; i < data.length; i++)
                {
                    initialDataToDisplay.push(
                        <tr key={data[i].ptaxMonthlyIdentity.ptax_slab_id}>
                        <td align='center'>{i+1}</td>
                        <td>{data[i].ptaxSlab.ptax_slab_desc}</td>
                        <td align='right'>{data[i].ptaxSlab.ptax_rate}</td>
                        <td align='right'>{data[i].no_employees}</td>
                        <td align='right'>{data[i].ptax_amount}</td>
                        </tr>);
                }
                this.setState({ 
                    paperHeight:'50vh',
                    showPTax:true,
                    ptaxToDisplay: initialDataToDisplay });   
                }
            else
            {
                await this.setState({ 
                    paperHeight:'10vh',
                    showPTax:false,
                    ptaxToDisplay: [],
                    showMessageBox:true,
                    title:'Information',
                    displayMessage: 'No records to display' 
                });   
            }
        } catch(err) 
        { 
            await this.setState({ 
                showMessageBox:true,
                title:'Error Information',
                displayMessage: err.message
            });   
        }
    }
    
    render() {
        const paperStyle={padding:20,height:this.state.paperHeight,width:550,margin:"40px 100px",border: '5px solid brown'}
        const divStyle = {
            border: '5px solid green',
            height: '35vh',
            overflow: 'auto'
          };

        return (
        <div>
            {this.state.showMessageBox ?
            <div>
                <MessageBoxForm title={this.state.title}>
                {this.state.displayMessage}
                </MessageBoxForm>
            </div> : null}
            <Paper style={paperStyle} variant="outlined">
                <Table>
                    <tr>
                        <td>
                            <TextField id="year" label='Year' placeholder='Year' variant='outlined' style ={{width: '30%'}}></TextField>
                        </td>
                        <td>
                            <Select id="monthsCombo" value={this.state.value} onChange={this.monthsComboChange}
                            style={{ border: '1px solid',width:'180px' }}>
                            {this.state.monthsToDisplay}
                            </Select>
                        </td>
                        <td>
                            <Button type='submit' color='primary' variant='contained' 
                            onClick={() => { this.printPTAX() }}>Print PTAX</Button>
                        </td>
                    </tr>
                </Table>
                <br/>
                { this.state.showPTax
                    ?<div style={divStyle}>
                    <Table id='tablePTAX' border='1'>
                        {this.state.ptaxToDisplay}
                    </Table>
                    </div>
                      : null
                }
            </Paper>
         </div>
        );
    }
}

export default PrintPTAXMonthlyForm;