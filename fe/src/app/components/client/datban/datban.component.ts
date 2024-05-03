import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { DatBanService } from 'src/app/_service/datban.service';

@Component({
  selector: 'app-datban',
  templateUrl: './datban.component.html',
  styleUrls: ['./datban.component.css'],
  providers: [MessageService]

})
export class DatbanComponent implements OnInit {
  nguoiLon: number = 0;
  treEm: number = 0;
  ngayDen: any;
  gioDen: any;
  gmail: string = '';
  sdt: string = '';
  ten: string = '';
  ghiChu: string = ''

  constructor(private datBanService: DatBanService) {} // Inject DatBanService

  ngOnInit(): void {}

  DatBan() {
    // Kiểm tra các trường không được để trống hoặc null
    if (this.nguoiLon > 0 &&this.treEm > 0 && this.ngayDen &&this.gioDen &&this.gmail &&this.sdt &&this.ten &&this.ghiChu) 
    {
      // Gọi phương thức createDatban từ DatBanService và truyền tham số vào
      this.datBanService.createDatban(
        this.nguoiLon,
        this.treEm,
        this.ngayDen,
        this.gioDen,
        this.gmail,
        this.sdt,
        this.ten,
        this.ghiChu
      ).subscribe(
        (res) => {
          // Xóa các giá trị từ các input
          this.nguoiLon = 0;
          this.treEm = 0;
          this.ngayDen = null;
          this.gioDen = null;
          this.gmail = '';
          this.sdt = '';
          this.ten = '';
          this.ghiChu = '';
          // Hiển thị thông báo đặt bàn thành công
          alert('Đặt bàn thành công!');
        },
      );
    } else {
      alert('Vui lòng điền đầy đủ thông tin đặt bàn.');
    }
  }
}
