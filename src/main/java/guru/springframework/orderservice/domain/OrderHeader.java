package guru.springframework.orderservice.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@AttributeOverrides({
    @AttributeOverride(
        name = "billToAddress.address",
        column = @Column(name="bill_to_address")
    ),
    @AttributeOverride(
        name = "billToAddress.city",
        column = @Column(name="bill_to_city")
    ),
    @AttributeOverride(
        name = "billToAddress.state",
        column = @Column(name="bill_to_state")
    ),
    @AttributeOverride(
        name = "billToAddress.zipCode",
        column = @Column(name="bill_to_zip_code")
    ),
    @AttributeOverride(
        name = "shippingAddress.address",
        column = @Column(name="shipping_address")
    ),
    @AttributeOverride(
        name = "shippingAddress.city",
        column = @Column(name="shipping_city")
    ),
    @AttributeOverride(
        name = "shippingAddress.state",
        column = @Column(name="shipping_state")
    ),
    @AttributeOverride(
        name = "shippingAddress.zipCode",
        column = @Column(name="shipping_zip_code")
    )
})
public class OrderHeader extends BaseEntity {

  @Embedded
  private Address billToAddress;

  @Embedded
  private Address shippingAddress;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @OneToMany(mappedBy = "orderHeader", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  private Set<OrderLine> orderLines = new HashSet<>();

  @ManyToOne
  private Customer customer;

  @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "orderHeader")
  private OrderApproval orderApproval;

  public void setOrderApproval(OrderApproval orderApproval) {
    this.orderApproval = orderApproval;
    orderApproval.setOrderHeader(this);
  }

  public void addOrderLine(OrderLine orderLine) {
    if (orderLines == null) {
      orderLines = new HashSet<>();
    }

    orderLines.add(orderLine);
    orderLine.setOrderHeader(this);
  }
}
