package com.example.nhahang.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nhahang.config.Config;
import com.example.nhahang.entity.Order;
import com.example.nhahang.entity.OrderDetail;
import com.example.nhahang.entity.Orderstatus;
import com.example.nhahang.entity.Product;
import com.example.nhahang.entity.Voucher;
import com.example.nhahang.excel.OrderExcelExport;
import com.example.nhahang.model.request.CreateOrderDetailRequest;
import com.example.nhahang.model.request.CreateOrderRequest;
import com.example.nhahang.model.request.CreateProductRequest;
import com.example.nhahang.model.response.MessageResponse;
import com.example.nhahang.service.OderdetailService;
import com.example.nhahang.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*",maxAge = 3600)
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OderdetailService oderdetailService;
    
    


    @GetMapping("/")
    @Operation(summary="Lấy ra danh sách đặt hàng")
    public ResponseEntity<List<Order>> getList(){
        List<Order> list = orderService.getList();

        return ResponseEntity.ok(list);
    }
    
    @GetMapping("/excel/{id}")
    @Operation(summary="Lấy ra danh sách đơn hàng kèm chi tiết theo id đơn hàng")
    public ResponseEntity<List<Order>> getListOrder(@PathVariable long id){
        List<Order> list = orderService.getListOrder(id);
        return ResponseEntity.ok(list);
    }
//    @GetMapping("/excelorder/{id}")
//	@Operation(summary="Xuất excel chi tiết đơn hàng")
//    public ModelAndView exportToExcel(@PathVariable long id,HttpServletResponse response) {      
//        List<Order> orders = orderService.getListOrder(id);
//        ModelAndView modelAndView = new ModelAndView(new OrderExcelExport(), "orders", orders);
//        return modelAndView;
//    }
//    @GetMapping("/excelorder/{id}")
//    @Operation(summary="Xuất excel chi tiết đơn hàng")
//    public ModelAndView exportToExcel(@PathVariable long id, HttpServletResponse response) {
//        List<Order> orders = orderService.getListOrder(id);
//        List<OrderDetail> orderDetails = oderdetailService.getOrderDetailsByOrderId(id);
//
//        ModelAndView modelAndView = new ModelAndView(new OrderExcelExport(), "orders", orders);
//        modelAndView.addObject("orderDetails", orderDetails);
//
//        return modelAndView;
//    }
    @GetMapping("/excelorder/{id}")
    @Operation(summary="Xuất excel chi tiết đơn hàng")
    public ModelAndView exportToExcel(@PathVariable long id, HttpServletResponse response) {      
        List<Order> orders = orderService.getListOrder(id);
        List<OrderDetail> orderDetails = oderdetailService.getOrderDetailsByOrderId(id); 

        ModelAndView modelAndView = new ModelAndView(new OrderExcelExport(), "orders", orders);
        modelAndView.addObject("orderDetails", orderDetails); // Truyền danh sách orderDetails vào view
        return modelAndView;
    }


    
    
    @GetMapping("/status")
    @Operation(summary="Lấy ra danh sách trạng thái")
    public ResponseEntity<List<Orderstatus>> getListstatus(){
        List<Orderstatus> list = orderService.getListstatus();

        return ResponseEntity.ok(list);
    }
    @PutMapping("/update/{id}")
    @Operation(summary="Tìm sản phẩm bằng id và cập nhật trạng thái sản phẩm đó")
    public ResponseEntity<Order> updateOrder(@PathVariable long id,@RequestBody CreateOrderRequest request){
    	Order order = orderService.updateOrder(id, request);

        return ResponseEntity.ok(order);
    }
    @GetMapping("/user")
    @Operation(summary="Lấy ra danh sách đặt hàng của người dùng bằng username")
    public ResponseEntity<List<Order>> getListByUser(@RequestParam("username") String username){
        List<Order> list = orderService.getOrderByUser(username);

        return ResponseEntity.ok(list);
    }
    public ResponseEntity<?> checkOrder(@PathVariable(name = "code") String code) {
        try {
            return orderService.checkOrder(code);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new HashMap<>());
        }
    }

    @DeleteMapping("removeOrder/{code}")
    public ResponseEntity<?> deleteOrder(@PathVariable(name = "code") String code) {
        try {
            return orderService.removeOrder(code);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    @Operation(summary = "Đặt hàng sản phẩm")
    public ResponseEntity<?> placeOrder(@RequestBody CreateOrderRequest request, HttpServletRequest httpServletRequest) {
        try {
            long total = 0;
            // Tính tổng tiền
            for (CreateOrderDetailRequest orderDetail : request.getOrderDetails()) {
                total += ((orderDetail.getPrice() * (orderDetail.getSoluong() >= 1 ? orderDetail.getSoluong() : 1)));
            }
            // Thiết lập các thông số cho VNPAY
            String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            String orderType = "other";
//            long amount = ((total * 24) * 1000) * 100;
//            long amount = total* 100;
            long amount = total*100;

            String vnp_TxnRef = Config.getRandomNumber(8);
            String vnp_IpAddr = Config.getIpAddress(httpServletRequest);

            String vnp_TmnCode = Config.vnp_TmnCode;

            // Tạo map chứa các tham số của VNPAY
            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(amount));
            vnp_Params.put("vnp_CurrCode", "VND");

            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
            vnp_Params.put("vnp_OrderType", orderType);

            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

            // Thiết lập thời gian tạo và hết hạn của đơn hàng
            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            // xây dựng dữ liệu băm và query string
            List fieldNames = new ArrayList(vnp_Params.keySet()); // Xây dựng danh sách các tham số
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder(); //dùng để gửi yêu cầu thanh toán đến VNPAY
            StringBuilder query = new StringBuilder(); // dùng để xây dựng URL thanh toán
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) { // Xây dựng chuỗi dữ liệu băm và chuỗi truy vấn
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            // Tạo chữ ký bảo mật và URL thanh toán
            String queryUrl = query.toString();
            String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
            Map<String, Object> job = new HashMap<>(); //Tạo phản hồi trả về:
            job.put("code", "00");
            job.put("message", "success");
            job.put("data", paymentUrl);
            request.setOrderCode(vnp_TxnRef);
            orderService.placeOrder(request);
            return ResponseEntity.ok(job);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new HashMap<>());
        }
    }
    
    
  

	/*
	 * @PostMapping("/create")
	 * 
	 * @Operation(summary="Đặt hàng sản phẩm") public ResponseEntity<?>
	 * placeOrder(@RequestBody CreateOrderRequest request){
	 * 
	 * orderService.placeOrder(request);
	 * 
	 * return ResponseEntity.ok(new MessageResponse("Order Placed Successfully!"));
	 * }
	 */
    
	/*
	 * // @GetMapping("/chitiet/{id}")
	 * // @Operation(summary="Lấy ra danh sách đơn hàng bằng id của ddơn hàng") //
	 * public ResponseEntity<List<OrderDetail>>
	 * getOrderDetailsByOrderId(@PathVariable long id){ // List<OrderDetail> list =
	 * oderdetailService.getOrderDetailsByOrderId(id); // return
	 * ResponseEntity.ok(list); // }
	 */}
