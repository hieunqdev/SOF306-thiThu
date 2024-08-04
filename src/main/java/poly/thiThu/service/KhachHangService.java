package poly.thiThu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import poly.thiThu.model.KhachHang;
import poly.thiThu.repository.KhachHangRepository;

@Service
public class KhachHangService {
	@Autowired
    private KhachHangRepository khachHangRepository;

	public List<Map<String, Object>> getAllKhachHangWithHangKhachHang() {
        return khachHangRepository.findAllWithHangKhachHang();
    }
	
	public Page<KhachHang> getKhachHangPage(Pageable pageable) {
        return khachHangRepository.findAll(pageable);
    }
	
	public KhachHang add(KhachHang khachHang) {
		return khachHangRepository.save(khachHang);
	}
	
	public void deleteKhachHangById(Long id) {
        khachHangRepository.deleteById(id);
    }
}
