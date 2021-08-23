import React, { Component } from 'react';
import { TextField, Table, Select , MenuItem, Button,Paper } from '@material-ui/core';
import MessageBoxForm from '../CommonComponents/MessageBoxForm';
import HelperMethods from '../CommonComponents/HelperMethods';

class PrintPaysheetForm extends Component {
    constructor(props) 
    {
        super(props);
        this.state = {
            monthsToDisplay:[],
            paysheetToDisplay:[],
            earnColsInGrid:[],
            dedColsInGrid:[],
            monthId:0,
            showPaysheet: false,
            paperHeight:'10vh',
            showMessageBox:false,
            title:'',
            displayMessage:''
        }
        this.monthsComboChange=this.monthsComboChange.bind(this);
        this.printPaysheet=this.printPaysheet.bind(this);
        this.displayPaysheet=this.displayPaysheet.bind(this);
    }

    async componentDidMount(){
        this.populateMonths();
        await document.getElementById("year").focus();
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
      this.displayPaysheet();
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

        if(document.getElementById("year").value.trim().length === 0)
        {
            await this.showMessage(true,'Error Information', 'Enter Year for Paysheet','year');
            return false;
        }
        if(this.state.monthId === 0)
        {
            await this.showMessage(true,'Error Information', 'Select Month for Paysheet','monthsCombo');
            return false;
        }
        return true;
    }

    printPaysheet()
    {
        var content = document.getElementById('printEmpPaySheet');
        var pri = document.getElementById('ifmPaySheetContentsToPrint').contentWindow;
        pri.document.open();
        pri.document.write(content.innerHTML);
        pri.document.close();
        pri.focus();
        pri.print();

    }

    async displayPaysheet()
    {
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
            
            var url= HelperMethods.GetServerIP() + 'api/paysheet/'  + monthId + '/' + year;
            
            const resp = await fetch(url,requestOptions);
            var data = await resp.json();
            if(data!=null && data.length > 0)
            {
                for (var i = 0; i < data.length; i++)
                {
                    if (i === 0)
                    {
                        this.setHeaders(data,initialDataToDisplay);
                    }
                    this.setRecords(data,i,initialDataToDisplay);
                }
                this.setState({ 
                    paperHeight:'100vh',
                    showPaysheet:true,
                    paysheetToDisplay: initialDataToDisplay });   
                }
            else
            {
                await this.setState({ 
                    paperHeight:'10vh',
                    showPaysheet:false,
                    paysheetToDisplay: []
                }); 
                await this.showMessage(true,'Information', 'No records to display','');
            }
        } catch(err) 
        {
            await this.setState({ 
                paperHeight:'10vh',
                showPaysheet:false,
                paysheetToDisplay: []
            }); 
            await this.showMessage(true,'Error Information', err.message,'');
        }
    }
    
    async setRecords(data,pos,initialDataToDisplay)
    {
        var empDetailsValues=JSON.parse(data[pos].emp_detail_values);
        var empName=empDetailsValues[0].emp_name;
        /*if(empDetailsValues[0].emp_code !=='E001')
        {
            return;
        }*/
        var ps_values = JSON.parse(data[pos].pay_sheet_values);
        var earnDedcols = JSON.parse(data[0].pay_sheet_columns);
        var totEarnCols=earnDedcols[0].no_earn_cols;
        var totDedCols=earnDedcols[0].no_ded_cols;
        var diffCols=totEarnCols - totDedCols;

        initialDataToDisplay.push(<tr></tr>);
        initialDataToDisplay.push(<td>{pos+1}</td>);
        initialDataToDisplay.push(<td>{empName}</td>);
        var x=0,y=0;
        var oneBlankSpace=true;
        
        if(this.state.earnColsInGrid.length>0 && ps_values.length>0)
        {
            for(x=0;x<this.state.earnColsInGrid.length;x++)
            {
                oneBlankSpace=true;
                for(y=0;y<ps_values.length;y++)
                {
                    if(this.state.earnColsInGrid[x] === ps_values[y].earn_ded_id)
                    {
                        initialDataToDisplay.push(<td align='right'>{ps_values[y].amount.toLocaleString()}</td>);
                        oneBlankSpace=false;
                        break;
                    }
                }
                if(oneBlankSpace){
                    initialDataToDisplay.push(<td align='right'>{0}</td>);
                    oneBlankSpace=false;
                }
            }
        }
        if(diffCols<0)
        {
            for(y=0;y<-diffCols;y++)
            {
                initialDataToDisplay.push(<td align='right'>{0}</td>)
            }
        }
        initialDataToDisplay.push(<td align='right'>{data[pos].total_earn_amount.toLocaleString()}</td>);
        initialDataToDisplay.push(<td>&nbsp;</td>)

        if(this.state.dedColsInGrid.length>0 && ps_values.length>0)
        {
            initialDataToDisplay.push(<tr></tr>);
            initialDataToDisplay.push(<td>&nbsp;</td>);
            initialDataToDisplay.push(<td>&nbsp;</td>);
            for(x=0;x<this.state.dedColsInGrid.length;x++)
            {
                oneBlankSpace=true;
                for(y=0;y<ps_values.length;y++)
                {
                    if(this.state.dedColsInGrid[x] === ps_values[y].earn_ded_id)
                    {
                        initialDataToDisplay.push(<td align='right'>{ps_values[y].amount.toLocaleString()}</td>);
                        oneBlankSpace=false;
                        break;
                    }
                }
                if(oneBlankSpace){
                    initialDataToDisplay.push(<td align='right'>{0}</td>);
                    oneBlankSpace=false;
                }
            }
        }
        if(diffCols>0)
        {
            for(y=0;y<diffCols;y++)
            {
                initialDataToDisplay.push(<td align='right'>{0}</td>)
            }
        }
        initialDataToDisplay.push(<td align='right'>{data[pos].total_ded_amount.toLocaleString()}</td>);
        initialDataToDisplay.push(<td align='right'>{data[pos].net_amount.toLocaleString()}</td>)
    }

    async setHeaders(data,initialDataToDisplay)
    {
        var earnDedcols = JSON.parse(data[0].pay_sheet_columns);
        earnDedcols.sort(this.GetSortOrder("earn_ded_priority"))
        var totEarnCols=earnDedcols[0].no_earn_cols;
        var totDedCols=earnDedcols[0].no_ded_cols;
        var totCols=totEarnCols + totDedCols;
        var diffCols=totEarnCols - totDedCols;
        let heading=false;
        let earnCols=[];
        let dedCols=[];
        
        for(var y=0; y<totCols; y++)
        {
            if(heading===false)
            {
                initialDataToDisplay.push(<th>Slno</th>);
                initialDataToDisplay.push(<th>Employee</th>);
                heading=true;
            }
            if(earnDedcols[y].earn_ded_type === 'E')
            {
                initialDataToDisplay.push(<th>{earnDedcols[y].earn_ded_code}</th>);
                earnCols.push(earnDedcols[y].earn_ded_id);
            }
        }

        if(diffCols<0)
        {
            for(y=0;y<-diffCols;y++)
            {
                initialDataToDisplay.push(<th>&nbsp;</th>)
            }
        }
        initialDataToDisplay.push(<th>Tot. Earn.</th>)
        initialDataToDisplay.push(<th>&nbsp;</th>)

        heading=false;
        
        for(y=0; y<totCols; y++)
        {
            if(earnDedcols[y].earn_ded_type === 'D')
            {
                if(heading===false)
                {
                    initialDataToDisplay.push(<tr></tr>);
                    initialDataToDisplay.push(<th>&nbsp;</th>);
                    initialDataToDisplay.push(<th>&nbsp;</th>);
                    heading=true;
                }
                initialDataToDisplay.push(<th>{earnDedcols[y].earn_ded_code}</th>);
                dedCols.push(earnDedcols[y].earn_ded_id);
            }
        }

        if(diffCols>0)
        {
            for(y=0;y<diffCols;y++)
            {
                initialDataToDisplay.push(<th>&nbsp;</th>)
            }
        }
        initialDataToDisplay.push(<th>Tot. Ded.</th>)
        initialDataToDisplay.push(<th>Net Amt.</th>)

        this.setState({
            earnColsInGrid:earnCols,
            dedColsInGrid:dedCols
        });
        return initialDataToDisplay;
    }

    GetSortOrder(prop) {    
        return function(a, b) {    
            if (a[prop] > b[prop]) {    
                return 1;    
            } else if (a[prop] < b[prop]) {    
                return -1;    
            }    
            return 0;    
        }    
    }    

    render() {
        const paperStyle={padding:20,height:this.state.paperHeight,width:800,margin:"40px 100px",border: '5px solid brown'}
        const divStyle = {
            border: '5px solid green',
            height: '90vh',
            overflow: 'auto'
          };

        return (
        <div>
            <Paper style={paperStyle} variant="outlined">
                {this.state.showMessageBox ?
                <div>
                    <MessageBoxForm title={this.state.title}>
                    {this.state.displayMessage}
                    </MessageBoxForm>
                </div> : null}
                <Table>
                    <tr>
                        <td>
                            <TextField id="year" label='Year' placeholder='Year' variant='outlined' style ={{width: '30%'}} inputProps={{ maxLength: 4 }} size="small"></TextField>
                        </td>
                        <td>
                            <Select id="monthsCombo" value={this.state.value} onChange={this.monthsComboChange}
                            style={{ border: '1px solid',width:'180px' }}>
                            {this.state.monthsToDisplay}
                            </Select>
                        </td>
                        <td>
                            <Button type='submit' color='primary' variant='contained' 
                            onClick={() => { this.printPaysheet() }}>Print Paysheet</Button>
                        </td>
                    </tr>
                </Table>
                <br/>
                { this.state.showPaysheet
                    ?<div id="printEmpPaySheet" style={divStyle}>
                    <Table id='tablePaysheet' border='1'>
                        {this.state.paysheetToDisplay}
                    </Table>
                    </div>
                      : null
                }
                <iframe id="ifmPaySheetContentsToPrint" title="Paysheet" 
                    style={{ height: '0px', width: '0px', position: 'absolute' }}>
                </iframe>
            </Paper>
         </div>
        );
    }
}

export default PrintPaysheetForm;