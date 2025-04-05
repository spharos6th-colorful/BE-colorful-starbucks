package colorful.starbucks.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
@RequiredArgsConstructor
public class OrderCodeGenerator {

    private final DataSource dataSource;

    public synchronized Long generate() {
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);


            PreparedStatement selectStmt = conn.prepareStatement("SELECT next_val FROM order_code_seq WHERE name = ?");
            selectStmt.setString(1, "order_code");
            ResultSet rs = selectStmt.executeQuery();
            rs.next();
            long nextVal = rs.getLong(1);


            PreparedStatement updateStmt = conn.prepareStatement("UPDATE order_code_seq SET next_val = ? WHERE name = ?");
            updateStmt.setLong(1, nextVal + 1);
            updateStmt.setString(2, "order_code");
            updateStmt.executeUpdate();

            conn.commit();
            return nextVal;
        } catch (Exception e) {
            throw new RuntimeException("주문번호 시퀀스 생성 실패", e);
        }
    }
}
