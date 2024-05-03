import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SERVER_DOMAIN } from './domain.service';
import { map } from 'rxjs/operators';


const DATBAN_API = SERVER_DOMAIN + "/api/datban/";


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class DatBanService {

  constructor(private http: HttpClient) { }

//lấy ra tất cả
  getList():Observable<any>{
    return this.http.get(DATBAN_API,httpOptions);
  }

  
// láy theo id
  getDatban(id: number):Observable<any>{
    return this.http.get(DATBAN_API + id,httpOptions);
  }
  createDatban(nguoilon: number,treem: number,ngayden: Date, gioden: string,gmail:string,sdt:string,ten:string,ghichu:string):Observable<any>{
    return  this.http.post(DATBAN_API +'create',{nguoilon,treem,ngayden,gioden,gmail,sdt,ten,ghichu},httpOptions);
  }

  updateDatban(id: number,nguoilon: number,treem: number,ngayden: Date, gioden: string,gmail:string,sdt:string,ten:string,ghichu:string):Observable<any>{
    return this.http.put(DATBAN_API + 'update/' +id,{id,nguoilon,treem,ngayden,gioden,gmail,sdt,ten,ghichu},httpOptions);
  }

  deleleDatban(id: number){
    return this.http.delete(DATBAN_API + 'delete/' + id,httpOptions);
  }

}
