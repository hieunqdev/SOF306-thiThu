package poly.thiThu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import poly.thiThu.model.KhachHang;
import poly.thiThu.service.KhachHangService;

@RestController
@RequestMapping("/api/khachhang")
public class KhachHangController {
	@Autowired
    private KhachHangService khachHangService;

    @GetMapping("all")
    public List<Map<String, Object>> getAllKhachHang() {
        return khachHangService.getAllKhachHangWithHangKhachHang();
    }
    
    @GetMapping("/page")
    public Page<KhachHang> getAllKhachHang(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return khachHangService.getKhachHangPage(pageable);
    }
    
    @PostMapping("/add")
    public KhachHang addKhachHang(@RequestBody KhachHang khachHang) {
        if (khachHang.getTenKhachHang().isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tên khách hàng không được để trống");
        }
        if (khachHang.getSoDienThoai().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Số điện thoại không được để trống");
        }
        if (khachHang.getGioiTinh().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Giới tính không được để trống");
        }
		KhachHang savedKhachHang = khachHangService.add(khachHang);  
        return savedKhachHang;
    }
    
    @DeleteMapping("/{id}")
    public String deleteKhachHang(@PathVariable Long id) {
        khachHangService.deleteKhachHangById(id);
        return "da xoa";
    }
}
