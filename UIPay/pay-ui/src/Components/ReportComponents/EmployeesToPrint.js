import React from 'react';
import ReactToPrint from 'react-to-print';
import HelperMethods from '../CommonComponents/HelperMethods';
import { Table,Button,Paper } from '@material-ui/core';
import PrintIcon from '@material-ui/icons/Print';

export class EmployeesListToPrint extends React.PureComponent 
{
  constructor(props) 
  {
      super(props);
      this.state = {
          employeesToDisplay:[],
          noOfRecords:0,
          currentPage:1,
          postsPerPage:15
      }
      this.handleClick = this.handleClick.bind(this);
  }

  handleClick = value => () => {
    this.setState({currentPage:value});
  };


  componentDidMount(){
      this.DisplayAllEmployees();

  }

  async DisplayAllEmployees()
  {
      let initialDataToDisplay = [];
      const requestOptions = {
          crossDomain:true,
          method: 'GET',
          headers: { 'Content-Type': 'application/json',
                      'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
      };
      var url=HelperMethods.GetServerIP() + 'api/employees'
      try 
      {
        const response = await fetch(url,requestOptions);
        var data = await response.json();
        if(data!=null && data.length>0)
        {
          for(var i=0;i<=data.length-1;i++)
          {
              let gender='Male';
              if(data[i].gender === "F"){
                  gender='Female';
              }
              
              initialDataToDisplay.push(
                  <tr key={i+1}>
                  <td>{i+1}</td>
                  <td>{data[i].emp_code}</td>
                  <td>{data[i].emp_first_name + ' ' + data[i]?.emp_middle_name + ' ' + data[i].emp_last_name}</td>
                  <td>{gender}</td>
                  <td>{data[i]?.department?.department_name}</td>
                  <td>{data[i]?.designation?.designation_name}</td>
                  </tr>);
          }
          this.setState({
              employeesToDisplay: initialDataToDisplay,
              noOfRecords:data.length
            });
        }
        else{
          alert("No Employees to display");
        }
      } catch(err) {
          alert(err.message);
      }
  }

  render()
  {
    const indexOfLastPost=this.state.currentPage * this.state.postsPerPage;
    const indexOfFirstPost=indexOfLastPost - this.state.postsPerPage;
    const currentPosts=this.state.employeesToDisplay.slice(indexOfFirstPost,indexOfLastPost);
       
    // Logic for displaying page numbers
    const pageNumbers = [];
    for (let i = 1; i <= Math.ceil(this.state.employeesToDisplay.length / this.state.postsPerPage); i++) {
      pageNumbers.push(i);
    }

    const renderPageNumbers = pageNumbers.map(number => {
      return (
        <Button id={number} variant="outlined" onClick={this.handleClick(number)}>
          {number}
        </Button>
      );
    });

    return(
      <div>
        <Table>
          <tr>
            <th>Slno</th><th>Code</th><th>Name</th>
            <th>Gender</th><th>Department</th><th>Designation</th>
          </tr><br></br>
          {currentPosts}
        </Table><br/>
        <div align='right'>
          {renderPageNumbers}
        </div>
      </div>
    );
  }
}

export default class EmployeesToPrint extends React.PureComponent 
{
  render()
  {
    const paperStyle={padding:20,height:'80vh',width:800,margin:"20px 100px",
                        border: '5px solid brown', overflow: 'auto'}
    return(
      <Paper style={paperStyle} variant='outlined'>
        <div>
          <ReactToPrint
              trigger={() => {
                return <Button color="primary" variant="contained" size="small" startIcon={<PrintIcon/>}>Print Employee List</Button>;
              }}
              content={() => this.componentRef}
          />
          <EmployeesListToPrint ref={el => (this.componentRef = el)} />
        </div>
      </Paper>);
  }
}