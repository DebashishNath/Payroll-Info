import React, { Component } from 'react';
import MasterForm from './MasterForm';

class DesignationForm extends Component 
{
  constructor(props) 
  {
      super(props);
      this.state = 
      {
          FormName              :'Designation',
          LabelCode             :'Designation Code',
          LabelName             :'Designation Name',
          SaveDesignationUrl    :'http://192.168.43.241:8086/api/savedesignation',
          ListDesignationUrl    :'http://192.168.43.241:8086/api/masterdatas/Designation'
      }
  }
  render(){
      return (
          <div>
              <MasterForm FormName={this.state.FormName} LabelCode={this.state.LabelCode} 
                  LabelName={this.state.LabelName} UpdateMasterUrl={this.state.SaveDesignationUrl}
                  ListMasterUrl={this.state.ListDesignationUrl}/>
          </div>
      );
  }
}

export default DesignationForm;