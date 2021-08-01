import React, { Component } from 'react';
import MasterForm from './MasterForm';
import HelperMethods from '../CommonComponents/HelperMethods';

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
          SaveDesignationUrl    : HelperMethods.GetServerIP() + 'api/savedesignation',
          ListDesignationUrl    : HelperMethods.GetServerIP() + 'api/masterdatas/Designation'
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