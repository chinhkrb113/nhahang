import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { CategoryService } from 'src/app/_service/category.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
  providers: [MessageService]

})

export class CategoryComponent implements OnInit {

  listCategory : any;

  displayForm: boolean = false;

  deleteForm : boolean = false;

  onUpdate : boolean = false;

  categoryForm : any ={
    id: null,
    name : null,
    link:null
  }

  constructor(private messageService : MessageService,private categoryService: CategoryService){}

  ngOnInit(): void {
    this.getListCategory();
  }


  getListCategory(){
    this.categoryService.getListCategory().subscribe({
      next: res =>{
        this.listCategory = res;
        console.log(res);
      },error: err =>{
        console.log(err);
      }
    })
  }

  showForm(){
    this.onUpdate = false;
    this.categoryForm ={
      id : null,
      name : null,
      link:null
    }
    this.displayForm = true;
  }

  
 onUpdateForm(id: number,name : string){
      this.onUpdate = true;
      this.displayForm =true;
      this.categoryForm.id = id;
      this.categoryForm.name = name;
  }

  onDelete(id:number,name : string){
    this.deleteForm = true;
    this.categoryForm.id = id;
    this.categoryForm.name = name;
  }

  createCategory(){
    const {name,link} = this.categoryForm;
    this.categoryService.createCategory(name,link).subscribe({
      next: res =>{
        this.getListCategory();
        this.showSuccess("Tạo món ăn thành công!");
        this.displayForm = false;
      },error: err=>{
        this.showError(err.message);
      }
    })
  }


  updateCategory(){
    const {id,name,link} = this.categoryForm;
    this.categoryService.updateCategory(id,name,link).subscribe({
      next: res =>{
        this.getListCategory();
        this.showSuccess("Cập nhật món ăn thành công!");
        this.displayForm = false;
      },error: err =>{
        this.showError(err.message);
      }
    })
  }


  enableCategory(id : number){
    this.categoryService.enableCategory(id).subscribe({
      next: res =>{
        this.getListCategory();
        this.showSuccess("Cập nhật thành công!!");
      },error: err=>{
        this.showError(err.message);
      }
    })
  }


  deleteCategory(){
    const {id} = this.categoryForm;
    this.categoryService.deleteCategory(id).subscribe({
      next: res =>{
        this.getListCategory();
        this.showWarn("Xóa món ăn thành công!!");
        this.deleteForm = false;
      },error: err=>{
        this.showError(err.message);
      }
    })
  }

  showSuccess(text: string) {
    this.messageService.add({severity:'success', summary: 'Success', detail: text});
  }
  showError(text: string) {
    this.messageService.add({severity:'error', summary: 'Error', detail: text});
  }

  showWarn(text : string) {
    this.messageService.add({severity:'warn', summary: 'Warn', detail: text});
  }
}
