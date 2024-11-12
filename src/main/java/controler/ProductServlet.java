/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler;

import dao.SanPhamDAO;
import dao.TheLoaiDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.sanpham;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("username") == null)
        {
            session.getAttribute("username");
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        SanPhamDAO SanPhamDAO = new SanPhamDAO();
        TheLoaiDAO TheLoaiDAO = new TheLoaiDAO();
        String action = "list";
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }

        switch (action) {
            case "list":
                
                int pageSize = 5;
                int pageIndex =1;
                if(request.getParameter("page") !=null)
                {
                    pageIndex = Integer.parseInt(request.getParameter("page"));
                }
                
                
                int pageSum = (int) Math.ceil((double)SanPhamDAO.getAll().size()/pageSize);
                ArrayList<sanpham> dsSanPham = SanPhamDAO.getByPage(pageIndex, pageSize);
                request.setAttribute("dsSanPham", dsSanPham);
                request.setAttribute("pageSum",pageSum);
                request.setAttribute("pageIndex",pageIndex);
                break;
            case "add":

                if (request.getMethod().equals("GET")) {
                    request.setAttribute("dsLoai", TheLoaiDAO.getAll());
                } else if (request.getMethod().equals("POST")) {
                    String tensanpham = request.getParameter("tenhoa");
                    double gia = Double.parseDouble(request.getParameter("gia"));
                    Part part = request.getPart("hinh");
                    int maloai = Integer.parseInt(request.getParameter("maloai"));
                    
                    String realPath = request.getServletContext().getRealPath("assets/images/products");
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    part.write(realPath + "/" + filename);
                    
                    sanpham objInsert = new sanpham(0, tensanpham, gia, filename, maloai, new Date((new java.util.Date().getTime())));
                    if (SanPhamDAO.Insert(objInsert)) {
                        
                        request.setAttribute("success", "Thao tác thêm sản phẩm thành công");
                    } else {
                        request.setAttribute("error", "Thao tác thêm sản phẩm thất bại");
                    }
                    request.getRequestDispatcher("ManageProduct?action=list").forward(request, response);
                }
                break;
            case "edit":


                 if (request.getMethod().equalsIgnoreCase("get")) {
                    int masanpham = Integer.parseInt(request.getParameter("masanpham"));
                    request.setAttribute("sampham", SanPhamDAO.getById(masanpham));
                    request.setAttribute("dsLoai", TheLoaiDAO.getAll());
                } else if (request.getMethod().equalsIgnoreCase("post")) {
                    int masanpham = Integer.parseInt(request.getParameter("masanpham"));
                    String tensanpham = request.getParameter("tenhoa");
                    double gia = Double.parseDouble(request.getParameter("gia"));
                    Part part = request.getPart("hinh");
                    int maloai = Integer.parseInt(request.getParameter("maloai"));
                    String filename = request.getParameter("oldImg");
                    if (part.getSize() > 0) {
                        String realPath = request.getServletContext().getRealPath("/assets/images/products");
                        filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                        part.write(realPath + "/" + filename);

                    }                    
                    sanpham objUpdate =new sanpham(masanpham, tensanpham, gia, filename, maloai, new Date(new java.util.Date().getTime()));
                    if (SanPhamDAO.Update(objUpdate)) {
                        request.setAttribute("success", "Cập nhật sản phẩm thành công");
                    } else {
                        request.setAttribute("error", "Cập nhật sản phẩm thất bại");
                    }
                    request.getRequestDispatcher("ManageProduct?action=list").forward(request, response);

                }
                break;
            case "delete":
                
                int masanpham= Integer.parseInt(request.getParameter("mahoa"));
                if (SanPhamDAO.Delete(masanpham)) {
                    request.setAttribute("success", "Thao tác xóa sản phẩm thành công");
                } else {
                    request.setAttribute("error", "Thao tác xóa sản phẩm thất bại");
                }
                request.getRequestDispatcher("ManageProduct?action=list").forward(request, response);
                break;
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
    