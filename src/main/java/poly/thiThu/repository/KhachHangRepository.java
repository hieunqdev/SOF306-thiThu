package poly.thiThu.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.thiThu.model.KhachHang;

public interface KhachHangRepository extends JpaRepository<KhachHang, Long>{
	@Query(value = "select kh.makhachhang, kh.tenkhachhang, kh.sodienthoai, kh.gioitinh, hkh.mahang, hkh.tenhang\r\n"
	+ "from khachhang kh \r\n"
	+ "join hangkhachhang hkh\r\n"
	+ "on kh.hangkhachhang = hkh.mahang;", nativeQuery = true)
	List<Map<String, Object>> findAllWithHangKhachHang();
	
//	findAll: được cung cấp sẵn bởi JpaRepository để hỗ trợ phân trang
	Page<KhachHang> findAll(Pageable pageable);
}
