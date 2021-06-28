import React, { Component } from 'react';
import MasterForm from './MasterForm';

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
          SaveCategoryUrl :'http://192.168.43.241:8086/api/savecategory',
          ListCategoryUrl :'http://192.168.43.241:8086/api/masterdatas/Category'
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