package bitcamp.myapp.controller;

import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.service.StorageService;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
public class BoardController {

  private BoardService boardService;
  private String folderName = "board/";
  private StorageService storageService;


  public BoardController(
      BoardService boardService, StorageService storageService) {
    this.boardService = boardService;
    this.storageService = storageService;

  }

  @GetMapping("/board/form")
  public void form() {
  }

  @PostMapping("/board/add")
  public String add(
      Board board,
      MultipartFile[] files,
      HttpServletRequest req, HttpServletResponse res, HttpSession session) throws Exception {
    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인 하지 않았습니다.");
    }

    board.setWriter(loginUser);

    ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

    for (MultipartFile file : files) {
      if (file.getSize() == 0) {
        continue;
      }

      AttachedFile attachedFile = new AttachedFile();
      attachedFile.setFilename(UUID.randomUUID().toString());
      attachedFile.setOriginFilename(file.getOriginalFilename());

      HashMap<String, Object> options = new HashMap<>();
      options.put(StorageService.CONTENT_TYPE, file.getContentType());
      storageService.upload(folderName + attachedFile.getFilename(),
          file.getInputStream(),
          options);

      attachedFiles.add(attachedFile);
    }

    board.setAttachedFiles(attachedFiles);

    boardService.add(board);
    return "redirect:list";
  }

  @GetMapping("/board/list")
  public void list(Model model) throws Exception {
    List<Board> list = boardService.list();
    model.addAttribute("list", list);

  }

  @GetMapping("/board/view")
  public void view(int no, Model model) throws Exception {
    Board board = boardService.get(no);

    if (board == null) {
      throw new Exception("게시글이 존재하지 않습니다.");
    }

    boardService.increaseViewCount(board.getNo());
    model.addAttribute("board", board);
  }

  @PostMapping("/board/update")
  public String update(
      HttpSession session,
      int no,
      String title,
      String content,
      Part[] files) throws Exception {
    User loginUser = (User) session.getAttribute("loginUser");

    Board board = boardService.get(no);
    if (board == null) {
      throw new Exception("없는 게시글입니다.");
    } else if (loginUser == null || loginUser.getNo() > 10 && board.getWriter()
        .getNo() != loginUser.getNo()) {
      throw new Exception("변경 권한이 없습니다.");
    }

    board.setTitle(title);
    board.setContent(content);

    ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

    for (Part part : files) {
      if (part.getSize() == 0) {
        continue;
      }

      AttachedFile attachedFile = new AttachedFile();
      attachedFile.setFilename(UUID.randomUUID().toString());
      attachedFile.setOriginFilename(part.getSubmittedFileName());

      HashMap<String, Object> options = new HashMap<>();
      options.put(StorageService.CONTENT_TYPE, part.getContentType());
      storageService.upload(folderName + attachedFile.getFilename(),
          part.getInputStream(),
          options);

      attachedFiles.add(attachedFile);
    }

    board.setAttachedFiles(attachedFiles);

    boardService.update(board);
    return "redirect:list";
  }

  @GetMapping("/board/delete")
  public String delete(
      int no,
      HttpSession session) throws Exception {

    User loginUser = (User) session.getAttribute("loginUser");
    Board board = boardService.get(no);

    if (board == null) {
      throw new Exception("없는 게시글입니다.");
    } else if (loginUser == null || loginUser.getNo() > 10 && board.getWriter()
        .getNo() != loginUser.getNo()) {
      throw new Exception("삭제 권한이 없습니다.");
    }

    for (AttachedFile attachedFile : board.getAttachedFiles()) {
      try {
        storageService.delete(folderName + attachedFile.getFilename());

      } catch (Exception e) {
        System.out.printf("%s 파일 삭제 실패\n", folderName + attachedFile.getFilename());
      }
    }

    boardService.delete(no);
    return "redirect:list";
  }

  @GetMapping("/board/file/delete")
  public String fileDelete(
      HttpSession session,
      int fileNo,
      int boardNo) throws Exception {

    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인 하지 않았습니다.");
    }

    AttachedFile attachedFile = boardService.getAttachedFile(fileNo);
    if (attachedFile == null) {
      throw new Exception("없는 첨부파일입니다.");
    }

    Board board = boardService.get(attachedFile.getBoardNo());
    if (loginUser.getNo() > 10 && board.getWriter().getNo() != loginUser.getNo()) {
      throw new Exception("삭제 권한이 없습니다.");
    }

    try {
      storageService.delete(folderName + attachedFile.getFilename());

    } catch (Exception e) {
      System.out.printf("%s 파일 삭제 실패\n", folderName + attachedFile.getFilename());
    }

    boardService.deleteAttachedFile(fileNo);
    return "redirect:../view?no=" + boardNo;
  }


}
