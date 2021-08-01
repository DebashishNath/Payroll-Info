import React, { Component } from 'react';
import MasterForm from './MasterForm';
import HelperMethods from '../CommonComponents/HelperMethods';

class CategoryForm extends Component 
{
  constructor(props) 
  {
      super(props);
      this.state = 
      {
          FormName        :'Category',
          LabelCode       :'Category Code',
          LabelName       :'Category Name',
          SaveCategoryUrl : HelperMethods.GetServerIP() + 'api/savecategory',
          ListCategoryUrl : HelperMethods.GetServerIP() +'api/masterdatas/Category'
      }
  }
  render(){
      return (
          <div>
              <MasterForm FormName={this.state.FormName} LabelCode={this.state.LabelCode} 
                  LabelName={this.state.LabelName} UpdateMasterUrl={this.state.SaveCategoryUrl}
                  ListMasterUrl={this.state.ListCategoryUrl}/>
          </div>
      );
  }
}

export default CategoryForm;